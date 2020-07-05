<template>
  <div class="container">
    <h1 class="mt-5">Dostupni <b>recepti</b> </h1>
    <div class="form-group">
        <label for="problemi">Odaberi namirnice</label>
        <select multiple class="form-control" id="problemi" v-model="multipleSelections">
            <option v-for="grocerie in allGroceries" :key="grocerie.id"  v-bind:value="grocerie.id">{{grocerie.name}}</option>
        </select>
    </div>

    <div class="form-group">
        <label for="exampleFormControlSelect1">Tip pretrage</label>
        <select v-model="selected" class="form-control" id="exampleFormControlSelect1">
        <option value="1">Pronadji recept sa iskljucivo ovim namirnicama</option>
        <option value="2">Pronadji recept sa namirnicama i dodacima</option>
        </select>
    </div>
    <button type="button" class="btn btn-primary" @click="findAll">Pronadji recepte</button>

    <div class="container">
    <div class="row mt-5 recepie-card"   v-for="meal in meals" :key="meal.id">
        <div class="col-md-3">
        <img class="card-img-left" src="../../../public/images/r3.jpg" alt="Card image cap">
        </div>
        <div class="col-md-9">
        <h3>{{meal.name}}</h3>
        <p> {{meal.description}}<br />
        <span class="monsBold mt-2">{{meal.preparationTime}}min</span> | {{meal.mealType.mealType}}<br /></p>
        <a href="#" class="card-link"  @click="findMissing(meal.id)">Missing groceries</a>

        </div>
  </div>
</div>
  </div>
</template>

<script>
import {AXIOS} from '../../http-common'

export default {
  name: 'findMeal',
  components: {
    },
  data () {
    return {
      meals: [], 
      allGroceries: [],
      selected: 1,
      multipleSelections: [],
      missingGroceries: []
    }
  },
  mounted(){

    AXIOS.get('/groceries')
    .then(response => {
        this.allGroceries = response.data 
        console.log(this.allGroceries)
    })
    .catch(err => {
        console.log(err)
    })

  }
  ,
  methods: {
      findMissing(id) {
          console.log(id)
           const groceries = {
                'grocerieList': this.multipleSelections
            }

          AXIOS.post('/meal/missingGroceries/' + id, groceries)
            .then(response => {
                this.missingGroceries = response.data.groceries
                let i = 0
                let out = "Missing groceries: \n";
                for( i = 0; i < this.missingGroceries.length; i++) {
                    out += this.missingGroceries[i].name + " \n";
                    console.log(this.missingGroceries[i].name)
                }
                console.log(response.data.groceries)
                alert(out);
            })
            .catch(err => {
                console.log(err)
            })
      },
      findAll() {
          if(this.selected == 1) {
            const groceries = {
                'grocerieList': this.multipleSelections
            }

            console.log(groceries)
            AXIOS.post('/meal/hasAllGroceries', groceries)
            .then(response => {
                this.meals = response.data.meals
                console.log(this.meals)
            })
            .catch(err => {
                console.log(err)
            })

          }else if(this.selected == 2) {

            const groceries = {
                'grocerieList': this.multipleSelections
            }

            console.log(groceries)
            AXIOS.post('/meal/hasAllGroceriesAndMore', groceries)
            .then(response => {
                this.meals = response.data.meals
                console.log(this.meals)
            })
            .catch(err => {
                console.log(err)
            })
        }
         
      }

  }
}
</script>
