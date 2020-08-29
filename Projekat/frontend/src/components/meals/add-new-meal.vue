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
        <input type="text" class="form-control" id="description" placeholder="Opis" v-model="description">
    </div>

    <div class="form-group">
        <label for="Instrukcije">Instrukcije</label>
        <input type="text" class="form-control" id="instructions" placeholder="Instrukcije" v-model="instructions">
    </div>

    <div class="form-group">
        <label for="problemi">Namirnice</label>
        <select multiple class="form-control" id="problemi"  v-model="multipleSelections">
            <option v-for="grocerie in allGroceries" :key="grocerie.id"  v-bind:value="grocerie.id">{{grocerie.name}}</option>
        </select>
    </div>



    <br />
    <button type="button" class="btn btn-primary" @click="add">Dodaj namirnicu</button>

    </form>
  </div>

</template>

<script>
import {AXIOS} from '../../http-common'
import axios from 'axios'

export default {
  name: 'addMeal',
  data () {
    return {
      name: '',
      preparationTime: '',
      description: '',
      instructions: '',
      allGroceries: '',
      quantities: [],
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
        axios.get("https://api.spoonacular.com/food/products/22347?apiKey=061d5a8c7d224c0fa3f862880ec5402d")
        .then(response => {console.log(response)
        console.log('aa')});


  }
  ,
  methods: {

      addQuantities(e) {
            e.preventDefault()
            this.quantities = this.multipleSelections;

      },
       add (e) {
        e.preventDefault()

        let grocery;
        let i = 0;
        for (i = 0; i < this.multipleSelections.length; i++) {
            grocery = {
                'grocery_id' : this.multipleSelections[i],
                'quantity': 100
            }
            this.quantities.push(grocery);

        }

        const groceries = {
            'id': 100,
            'name': this.name,
            'description': this.description,
            'preparationTime': this.preparationTime,
            'instructions': this.instructions,
            'groceries': this.quantities
        }

        console.log(groceries)
        AXIOS.post('/meal', groceries)
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
