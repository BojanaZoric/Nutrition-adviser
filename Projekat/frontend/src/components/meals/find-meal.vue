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
    <div class="row mt-5 recepie-card mb-5"   v-for="meal in meals" :key="meal.id">
        <div class="col-md-1">
        </div>
        <div class="col-md-9">
        <h3>{{meal.name}}</h3>
        <p> {{meal.description}}<br />
        <span class="monsBold mt-2">{{meal.preparationTime}}min</span> <br /></p>
        <a href="#" class="card-link" v-if="selected != 1" @click="findMissing(meal.id)">Missing groceries</a>
        <a href="#" class="card-link"  @click="checkMeal(meal.id)">Check meal</a>
        <a href="#" class="card-link"  @click="addMeal(meal.id)">More details</a>
        <a href="#" class="card-link"  @click="eatMeal(meal.id)">Eat meal</a>

        </div>
</div>
  </div></div>

</template>

<script>
import {AXIOS} from '../../http-common'
import VueSimpleAlert from 'vue-simple-alert'

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
      missingGroceries: [],
      out: '',
      modalShow: false
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
    checkMeal(id) {
        AXIOS.get('/meal/checkMeal/' + id,{ headers: {"Authorization" : `Bearer ${localStorage.getItem('token')}`} })
            .then(response => {
              let out = "\n\n";
                if(response.data.length == 0) {
                    out += "NO ALARMS DETECTED"
                }
                else{
                  let i;
                  for(i = 0; i< response.data.length; i++) {
                      out += " * " + response.data[i].message + "\n";
                  }
                }
                console.log(response.data)
              VueSimpleAlert.alert(out, "Alert alarms");
console.log(response.data)
        })},

      findMissing(id) {


          console.log(id)
           const groceries = {
                'grocerieList': this.multipleSelections
            }

          AXIOS.post('/meal/missingGroceries/' + id, groceries)
            .then(response => {
                this.missingGroceries = response.data.groceries
                let i = 0
                let out = "\n";

                if(this.missingGroceries.length == 0) {
                    out += "NO MISSING GROCERIES"
                }
                for( i = 0; i < this.missingGroceries.length; i++) {
                    out += this.missingGroceries[i].name + " \n";
                    console.log(this.missingGroceries[i].name)
                }
                console.log(response.data.groceries)
                VueSimpleAlert.alert(out, "Missing groceries");
                this.out = out;
                this.modalShow = !this.modalShow;
            })
            .catch(err => {
                console.log(err)
            })
      },
      addMeal(id) {
      this.$router.push("/details/" + id)
    },eatMeal(id) {
        AXIOS.post('meal/add-user-meal/' + id)
          .then(response => {
              if(response.data === true) {
                VueSimpleAlert.alert("Vaš obrok je uspešno dodat!", "Eating alarm");
              }else {
                    VueSimpleAlert.alert("Već ste dodali više od 3 obroka u poslednjih 6 sati. Pokušajte kasnije!", "Eating alarm");

              }
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
