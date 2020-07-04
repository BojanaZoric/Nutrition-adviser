<template>
  <div class="container pt-5 pb-5">
    <h1>Dodavanje <span class="monsBold">namirnice</span></h1>

    <form>
      <div class="form-group">
        <label for="naziv">Naziv namirnice</label>
        <input type="text" class="form-control" id="naziv" placeholder="Naziv" v-model="name">
      </div>

      <div class="form-group">
        <label for="naziv">Kalorije</label>
        <input type="number" class="form-control" id="naziv" placeholder="Kalorije" v-model="calories">
      </div>

    <div class="form-group">
        <label for="proteinAmount">Količina proteina</label>
        <input type="number" class="form-control" id="proteinAmount" placeholder="Kalorije" v-model="proteinAmount">
    </div>

    <div class="form-group">
        <label for="carbohydrateAmount">Količina ugljenih hidrata</label>
        <input type="number" class="form-control" id="carbohydrateAmount" placeholder="Kalorije" v-model="carbohydrateAmount">
    </div>
    <div class="form-check">
        <input type="checkbox" class="form-check-input" id="glutenFree" v-model="glutenFree">
        <label class="form-check-label" for="glutenFree" >Bez glutena</label>
    </div>
    <br />
    <button type="button" class="btn btn-primary" @click="add">Dodaj namirnicu</button>

    </form>
  </div>

</template>

<script>
import {AXIOS} from '../../http-common'

export default {
  name: 'addGrocerie',
  data () {
    return {
      name: '',
      calories: 0,
      proteinAmount: 0,
      carbohydrateAmount: 0,
      glutenFree: true
    }
  },
  mounted(){

  }
  ,
  methods: {
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
        AXIOS.post('/groceries', groceries)
        .then(response => {
            console.log(response)
        })
        .catch(err => {
            console.log(err)
        })
        }

  }
}
</script>
