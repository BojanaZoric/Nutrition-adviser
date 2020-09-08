<template>
  <div class="container pt-5 pb-5">
    <h1>Izmena <span class="monsBold">obroka</span></h1>

    <form>
      <div class="form-group">
        <label for="naziv">Naziv obroka</label>
        <input type="text" class="form-control" id="naziv" placeholder="Naziv" v-model="meal.name">
      </div>

    <div class="form-group">
        <label for="preparationTime">Vreme pripreme</label>
        <input type="number" class="form-control" id="preparationTime" placeholder="Vreme pripreme" v-model="meal.preparationTime">
    </div>

    <div class="form-group">
        <label for="description">Opis</label>
        <input type="text" class="form-control" id="description" placeholder="Opis" v-model="meal.description">
    </div>

    <div class="form-group">
        <label for="Instrukcije">Instrukcije</label>
        <input type="text" class="form-control" id="instructions" placeholder="Instrukcije" v-model="meal.instructions">
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
        <select v-model="selectedMeasure" >
          <option v-bind:value="item.uri" v-for="item in measures" v-bind:key="item.measureUrl">{{item.label}}</option>
        </select>
      </div>
      <div class="col-md-4">
        <input class="form-control" type="number" v-model="selectedQuantity" placeholder="Quatity" >
      </div>
      <div class="col-md-3">
            <button type="button" class="btn btn-primary" @click="addQuantity">Dodaj namirnicu</button>
      </div>
    </div>

    <div v-if="meal.groceries !== []" class="ml-5 mb-5 mt-5 mr-5 row">
      <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Namirnica</th>
          <th scope="col">Merna jedinica</th>

          <th scope="col">Kolicina</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in meal.groceries" v-bind:key="item.grocerie.id">
          <th scope="row"></th>
          <td>{{item.grocerie.name}}</td>
                    <td>{{item.grocerie.measure}}</td>

          <td>{{item.quantity}}</td>
                <td>    <button type="button" class="btn btn-danger" @click="deleteG(item.grocerie.id)">Obriši</button>
</td>
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

    <div v-if="meal.steps !== []" class="ml-5 mb-5 mt-5 mr-5 row">
      <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Naziv</th>
          <th scope="col">Instrukcija</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in meal.steps" v-bind:key="item.name">
          <th scope="row"></th>
          <td>{{item.name}}</td>
          <td>{{item.instruction}}</td>
                    <td>    <button type="button" class="btn btn-danger" @click="deleteS(item.id)">Obriši</button>
</td>

        </tr>
      </tbody>
    </table>
    </div>

    <br />
    <button type="button" class="btn btn-primary" @click="add">Izmeni obrok</button>
    {{groc}}
    </form>
  </div>

</template>

<script>

import _ from 'underscore'
import VueBootstrapTypeahead from 'vue-bootstrap-typeahead'
import {AXIOS} from '../../http-common'
import Axios from 'axios'

const API_URL = 'https://api.edamam.com/api/food-database/v2/parser?ingr=:query&app_id=95c8a7ac&app_key=dafeb6c68eb8c49d26dea8136ed01401'

export default {
   components: {
        VueBootstrapTypeahead
    },
  name: 'editMeal',
  data () {
    return {
      addresses: [],
      addressSearch: '',
      selectedAddress: null,
      selectedAdressDetails: null,

      selectedQuantity: null,
      groc: null,
      stepName: '',
      stepInstuction: '',
      measures: [],
      selectedMeasure: '',
      saved: '',
      meal :null
    }
  },
  mounted() {
    Axios.get(`http://localhost:8081/meal/` +  this.$route.params.id)
    .then(response => {
      this.meal = response.data;
      this.meal.steps.sort(function(a, b) {
  return a.id - b.id ;
})
      console.log(this.meal)
    })
    .catch(e => {
      this.errors.push(e)
    })
  },
  methods: {
    deleteG(id) {

          this.meal.groceries = this.meal.groceries.filter(x => {
  return x.grocerie.id != id;});

        },
         deleteS(id) {

          this.meal.steps = this.meal.steps.filter(x => {
  return x.id != id;});

        },
    async getAddresses(query) {


      const res = await fetch(API_URL.replace(':query', query))
      const suggestions = await res.json()
      console.log(suggestions.hints)
      this.addresses = suggestions.hints
    },
    handleChange(event) {
      this.selectedAddress = event;
      this.measures = this.selectedAddress.measures;
      console.log(this.selectedAddress.measures)

    },

    addStep() {
      const step = {
        'name': this.stepName,
        'instruction': this.stepInstuction
      }
      this.meal.steps.push(step);
    },
    addQuantity(){

          console.log(this.selectedAddress)
          const requestOptions = {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify( {"ingredients": [
            {
              "quantity": parseFloat(this.selectedQuantity),
              "measureURI": this.selectedMeasure,
              "foodId": this.selectedAddress.food.foodId
            }

          ]}
          )};
          console.log(requestOptions)
        fetch("https://api.edamam.com/api/food-database/v2/nutrients?app_id=95c8a7ac&app_key=dafeb6c68eb8c49d26dea8136ed01401", requestOptions)
          .then(response => response.json()).then(data => {
            this.selectedAdressDetails = data;
             console.log(data);

              this.addQ();
             });



    },
    addQ(){

      console.log(this.selectedAdressDetails)
      let allergens = [];

      if(this.selectedAdressDetails.healthLabels.find(element => element === 'GLUTEN_FREE') === undefined)
        allergens.push(8);
      else if(this.selectedAdressDetails.healthLabels.find(element => element === 'PESCATARIAN') === undefined)
        allergens.push(13);
      else if(this.selectedAdressDetails.healthLabels.find(element => element === 'DAIRY_FREE') === undefined)
      allergens.push(4);
      else if(this.selectedAdressDetails.healthLabels.find(element => element === 'CRUSTACEAN_FREE') === undefined)
      allergens.push(3);
      else if(this.selectedAdressDetails.healthLabels.find(element => element === 'EGG_FREE') === undefined)
      allergens.push(5);
      else if(this.selectedAdressDetails.healthLabels.find(element => element === 'FISH_FREE') === undefined)
      allergens.push(6);
      else if(this.selectedAdressDetails.healthLabels.find(element => element === 'FODMAP_FREE') === undefined)
      allergens.push(7);
      else if(this.selectedAdressDetails.healthLabels.find(element => element === 'KOSHER') === undefined)
      allergens.push(9);
      else if(this.selectedAdressDetails.healthLabels.find(element => element === 'LUPINE_FREE') === undefined)
      allergens.push(10);
      else if(this.selectedAdressDetails.healthLabels.find(element => element === 'NO_OIL_ADDED') === undefined)
      allergens.push(11);
      else if(this.selectedAdressDetails.healthLabels.find(element => element === 'PEANUT_FREE') === undefined)
      allergens.push(12);
      else if(this.selectedAdressDetails.healthLabels.find(element => element === 'PORK_FREE') === undefined)
      allergens.push(14);
      else if(this.selectedAdressDetails.healthLabels.find(element => element === 'RED_MEAT_FREE') === undefined)
      allergens.push(15);
      else if(this.selectedAdressDetails.healthLabels.find(element => element === 'SESAME_FREE') === undefined)
        allergens.push(16);
else if(this.selectedAdressDetails.healthLabels.find(element => element === 'SHELLFISH_FREE') === undefined)
      allergens.push(17);
else if(this.selectedAdressDetails.healthLabels.find(element => element === 'SOY_FREE') === undefined)
        allergens.push(18);

      console.log(allergens)
      console.log(this.selectedAdressDetails)
      const groceries = {
            'name': this.selectedAddress.food.label + ' ' + (this.selectedAddress.food.brand === undefined ? '': '| BRAND - ' + this.selectedAddress.food.brand),
            'calories': this.selectedAdressDetails.calories / this.selectedQuantity,
            'proteinAmount': (this.selectedAdressDetails.totalNutrients.PROCNT !== undefined) ? this.selectedAdressDetails.totalNutrients.PROCNT.quantity/this.selectedQuantity  : 0,
            'carbohydrateAmount': (this.selectedAdressDetails.totalNutrients.CHOCDF !== undefined) ? this.selectedAdressDetails.totalNutrients.CHOCDF.quantity/this.selectedQuantity : 0,
            'glutenFree': (this.selectedAdressDetails.healthLabels.find(element => element === 'GLUTEN_FREE') !== undefined ? true : false),
            'diabetes': (this.selectedAdressDetails.healthLabels.find(element => element === 'GLUTEN_FREE') !== undefined ? true : false),
            'heartDisease': false,
            'highBloodPressure': false,
            'diet': (this.selectedAdressDetails.healthLabels.find(element => element === 'VEGAN') !== undefined ? 1 : 0),
            'allergens': allergens,
            'measure': this.selectedMeasure.substring(this.selectedMeasure.indexOf('_') + 1)
        }
        console.log(groceries)
        AXIOS.post('/groceries', groceries, { headers: {"Authorization" : `Bearer ${localStorage.getItem('token')}`} })
        .then(response => {
           this.saved = response.data;
           console.log(this.meal.groceries)
            let q = this.meal.groceries.find(q => q.grocerie.id === this.saved.id)
           if(q){
             q.quantity += parseInt(this.selectedQuantity)
           }else{
           const grocery = {
                'grocerie' : this.saved,
                'quantity': parseInt(this.selectedQuantity)
            }
            this.meal.groceries.push(grocery);

            }
        })
        .catch(err => {
            console.log(err)
        })
        console.log(this.meal.groceries)

    },
    add (e) {
        e.preventDefault()

        let i = 0;
        let groceriess = []
        for (i = 0; i < this.meal.groceries.length; i++) {
          const grocery = {
                'grocerie_id' : this.meal.groceries[i].grocerie.id,
                'quantity': parseInt(this.meal.groceries[i].quantity)
            }
            groceriess.push(grocery);
        }

        const groceries = {
            'id': this.meal.id,
            'name': this.meal.name,
            'description': this.meal.description,
            'preparationTime': this.meal.preparationTime,
            'instructions': this.meal.instructions,
            'groceries': groceriess,
            'steps': this.meal.steps
        }
        this.groc = groceries;
        console.log(groceries)
        AXIOS.put('/meal', groceries)
        .then(response => {
            this.allGroceries = response
                          this.$router.push("/details/" + response.data.id)

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
