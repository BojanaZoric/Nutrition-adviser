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

    <recepiesList :meals="meals"> </recepiesList>
  </div>
</template>

<script>
import recepiesList from '@/components/recepies/recepies-list'
import {AXIOS} from '../../http-common'

export default {
  name: 'findMeal',
  components: {
      recepiesList
    },
  data () {
    return {
      meals: [], 
      allGroceries: [],
      selected: 1,
      multipleSelections: []
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
