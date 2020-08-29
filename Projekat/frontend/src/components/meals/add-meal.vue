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

    <div>
    <vue-bootstrap-typeahead class="ml-5 mb-5 mt-5 mr-5"
      :data="addresses"
      v-model="addressSearch"
      size="lg"
      :serializer="s => s.food.label + ' ' + (s.food.brand === undefined ? '': '| BRAND - ' + s.food.brand)"
      placeholder="Unesite naziv namirice..."
      @hit='handleChange($event)'
    />


    <div v-if="selectedAddress" class="ml-5 mb-5 mt-5 mr-5 row">
      <div class="col-md-4">
{{ selectedAddress.food.label + ' ' + (selectedAddress.food.brand === undefined ? '': '| BRAND - ' + selectedAddress.food.brand) }}
      </div>
      <div class="col-md-4">
        <input class="form-control" type="number" v-model="selectedQuantity" placeholder="Quatity" >
      </div>
      <div class="col-md-3">
            <button type="button" class="btn btn-primary" @click="addQuantity">Dodaj namirnicu</button>
      </div>
    </div>

    <div v-if="quantities !== []" class="ml-5 mb-5 mt-5 mr-5 row">
      <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Namirnica</th>
          <th scope="col">Kolicina</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in quantities" v-bind:key="item.grocerie_id.id">
          <th scope="row"></th>
          <td>{{item.grocerie_id.name}}</td>
          <td>{{item.quantity}}</td>
        </tr>
      </tbody>
    </table>
    </div>
</div> <br /><br />
    <div class="ml-5 mb-5 mt-5 mr-5 row">
      <div class="col-md-12">
        <input class="form-control" type="text" v-model="stepName" placeholder="Naziv koraka" >

      </div>
      <div class="col-md-12">
        <input class="form-control" type="text" v-model="stepInstuction" placeholder="Opis koraka" >
      </div>
      <div class="col-md-3 mt-5">
            <button type="button" class="btn btn-primary" @click="addStep">Dodaj korak</button>
      </div>
    </div>

    <div v-if="steps !== []" class="ml-5 mb-5 mt-5 mr-5 row">
      <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Naziv</th>
          <th scope="col">Instrukcija</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in steps" v-bind:key="item.name">
          <th scope="row"></th>
          <td>{{item.name}}</td>
          <td>{{item.instruction}}</td>
        </tr>
      </tbody>
    </table>
    </div>


    <br />
    <button type="button" class="btn btn-primary" @click="add">Dodaj namirnicu</button>
    {{groc}}
    </form>
  </div>

</template>

<script>

import _ from 'underscore'
import VueBootstrapTypeahead from 'vue-bootstrap-typeahead'
import {AXIOS} from '../../http-common'

const API_URL = 'https://api.edamam.com/api/food-database/v2/parser?ingr=:query&app_id=95c8a7ac&app_key=dafeb6c68eb8c49d26dea8136ed01401'

export default {
   components: {
        VueBootstrapTypeahead
    },
  name: 'addMeal',
  data () {
    return {
      name: '',
      preparationTime: '',
      description: '',
      instructions: '',
      addresses: [],
      addressSearch: '',
      selectedAddress: null,
      selectedAdressDetails: null,
      quantities: [],
      selectedQuantity: null,
      groc: null,
      stepName: '',
      stepInstuction: '',
      steps: []
    }
  },
  methods: {
    async getAddresses(query) {


      const res = await fetch(API_URL.replace(':query', query))
      const suggestions = await res.json()
      console.log(suggestions.hints)
      this.addresses = suggestions.hints
    },
    handleChange(event) {
      this.selectedAddress = event;
      const requestOptions = {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify( {"ingredients": [
            {
              "quantity": 1,
              "measureURI": "http://www.edamam.com/ontologies/edamam.owl#Measure_unit",
              "foodId": this.selectedAddress.food.foodId
            }

          ]}
          )};
        fetch("https://api.edamam.com/api/food-database/v2/nutrients?app_id=95c8a7ac&app_key=dafeb6c68eb8c49d26dea8136ed01401", requestOptions)
          .then(response => response.json()).then(data => this.selectedAdressDetails = data);
          },

    addStep() {
      const step = {
        'name': this.stepName,
        'instruction': this.stepInstuction
      }
      this.steps.push(step);
    },
    addQuantity(){
      console.log(this.selectedAdressDetails)

      const groceries = {
            'name': this.selectedAddress.food.label + ' ' + (this.selectedAddress.food.brand === undefined ? '': '| BRAND - ' + this.selectedAddress.food.brand),
            'calories': this.selectedAdressDetails.calories,
            'proteinAmount': (this.selectedAdressDetails.totalNutrients.PROCNT !== undefined) ? this.selectedAdressDetails.totalNutrients.PROCNT.quantity : 0,
            'carbohydrateAmount': (this.selectedAdressDetails.totalNutrients.CHOCDF !== undefined) ? this.selectedAdressDetails.totalNutrients.CHOCDF.quantity : 0,
            'glutenFree': (this.selectedAdressDetails.healthLabels.find(element => element === 'GLUTEN_FREE') !== undefined ? true : false),
            'diabetes': (this.selectedAdressDetails.healthLabels.find(element => element === 'GLUTEN_FREE') !== undefined ? true : false),
            'heartDisease': false,
            'highBloodPressure': false,
            'diet': (this.selectedAdressDetails.healthLabels.find(element => element === 'VEGAN') !== undefined ? 1 : 0)
        }
        let saved;
        console.log(groceries)
        AXIOS.post('/groceries', groceries)
        .then(response => {
           saved = response.data;
           console.log(response.data)
           let q = this.quantities.find(q => q.grocerie_id.id === saved.id)
           if(q){
             q.quantity += parseInt(this.selectedQuantity)
           }else{
           const grocery = {
                'grocerie_id' : saved,
                'quantity': parseInt(this.selectedQuantity)
            }
            this.quantities.push(grocery);
            }
        })
        .catch(err => {
            console.log(err)
        })
        console.log(this.quantities)


    },
    add (e) {
        e.preventDefault()

        let i = 0;
        for (i = 0; i < this.quantities.length; i++) {
          let temp = this.quantities[i].grocerie_id.id;
          this.quantities[i].grocerie_id = temp;
        }

        const groceries = {
            'id': 100,
            'name': this.name,
            'description': this.description,
            'preparationTime': this.preparationTime,
            'instructions': this.instructions,
            'groceries': this.quantities,
            'steps': this.steps
        }
        this.groc = groceries;
        console.log(groceries)
        AXIOS.post('/meal', groceries)
        .then(response => {
            this.allGroceries = response
        })
        .catch(err => {
            console.log(err)
        })

        console.log('aaa')
        }
  },

  watch: {
    addressSearch: _.debounce(function(addr) { this.getAddresses(addr) }, 500)
  }
}
</script>
