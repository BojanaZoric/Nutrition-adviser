<template>
  <div class="container pt-5 pb-5">
    <h1>Dodavanje <span class="monsBold">obroka</span></h1>

    <form>
      <div class="form-group">
        <label for="naziv">Naziv namirnice</label>
        <input type="text" class="form-control" id="naziv" placeholder="Naziv" v-model="name">
      </div>

    <div class="form-group">
        <label for="preparationTime">Vreme pripreme</label>
        <input type="number" class="form-control" id="preparationTime" placeholder="Vreme pripreme" v-model="preparationTime">
    </div>

    <div class="form-group">
        <label for="description">Opis</label>
        <input type="number" class="form-control" id="description" placeholder="Opis" v-model="description">
    </div>

    <div class="form-group">
        <label for="Instrukcije">Instrukcije</label>
        <input type="number" class="form-control" id="instructions" placeholder="Instrukcije" v-model="instructions">
    </div>

    <div class="form-group">
    <label for="problemi">Namirnice</label>
    <select multiple class="form-control" id="problemi">
        <option v-for="grocerie in allGroceries" :key="grocerie.id">{{grocerie.name}}</option>
    </select>
    </div>
    <button type="button" class="btn btn-primary" @click="addQuantities">Dodaj kolicine</button>

    <br />
    <button type="button" class="btn btn-primary" @click="add">Dodaj namirnicu</button>

    </form>
  </div>

</template>

<script>
import {AXIOS} from '../../http-common'

export default {
  name: 'addMeal',
  data () {
    return {
      name: '',
      preparationTime: '',
      description: '',
      instructions: '',
      allGroceries: '',
      quantities: []
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

      addQuantities(e) {
            e.preventDefault()

      },
       add (e) {
        e.preventDefault()
        const groceries = {
            'name': this.name,
            'calories': this.calories,
            'proteinAmount': this.proteinAmount,
            'carbohydrateAmount': this.carbohydrateAmount,
            'glutenFree': this.glutenFree

        }

        console.log(groceries)
        AXIOS.post('/meals', groceries)
        .then(response => {
            this.allGroceries = response 
        })
        .catch(err => {
            console.log(err)
        })
        }

  }
}
</script>
