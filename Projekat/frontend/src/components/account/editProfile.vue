<template>
  <div class="container pt-5 pb-5">
    <h1>Korisnički <span class="monsBold">profil</span></h1>

    <form>
      <div class="form-group">
        <label for="visina">Visina</label>
        <input type="text" class="form-control" id="visina" placeholder="Visina" v-model="visina">
      </div>
      <div class="form-group">
        <label for="tezina">Tezina</label>
        <input type="text" class="form-control" id="tezina" placeholder="Tezina" v-model="tezina">
      </div>

       <div class="form-group">
          <label for="godiste">Godište</label>
          <input type="text" class="form-control" id="godiste" placeholder="Godište" v-model="godiste">
        </div>

         <div class="form-group">
            <label for="pol">Pol</label>
            <select class="form-control" id="pol" v-model="pol">
              <option value="male" selected>Muški</option>
              <option value="female">Ženski</option>
            </select>
          </div>
          <div class="form-group">
            <label for="alergije">Alergije na hranu</label>
            <select multiple class="form-control" id="alergije" v-model="selected">
              <option value=1>alcohol-free</option>
              <option value=2>celery-free</option>
              <option value=3>crustacean-free</option>
              <option value=4>dairy-free</option>
              <option value=5>egg-free</option>
              <option value=6>fish-free</option>
              <option value=7>fodmap-free</option>
              <option value=8>gluten-free</option>
              <option value=9>kosher</option>
              <option value=10>lupine-free</option>
              <option value=11>No-oil-added</option>
              <option value=12>peanut-free</option>
              <option value=13>pecatarian</option>
              <option value=14>pork-free</option>
              <option value=15>red-meat-free</option>
              <option value=16>sesame-free</option>
              <option value=17>shellfish-free</option>
              <option value=18>soy-free</option>

            </select>
          </div>
            <label for="alergije">Predispozicije</label>

          <div class="form-check">
              <input type="checkbox" class="form-check-input" id="glutenFree" v-model="glutenFree">
              <label class="form-check-label" for="glutenFree" >Bez glutena</label>
          </div>

          <div class="form-check">
              <input type="checkbox" class="form-check-input" id="diabetes" v-model="diabetes">
              <label class="form-check-label" for="diabetes" >Pogodno za dijabetičare</label>
          </div>

          <div class="form-check">
              <input type="checkbox" class="form-check-input" id="heartDisease" v-model="heartDisease">
              <label class="form-check-label" for="heartDisease" >Pogodna za srčane bolesnike</label>
          </div>

          <div class="form-check">
              <input type="checkbox" class="form-check-input" id="highBloodPressure" v-model="highBloodPressure">
              <label class="form-check-label" for="highBloodPressure" >Pogodna za ljude sa visokim pritiskom</label>
          </div>
          <button type="button" class="btn btn-primary" @click="add">Sačuvaj izmene</button>

    </form>
  </div>

</template>

<script>
import { AXIOS } from '../../http-common'
export default {
  name: 'editProfile',
  data () {
    return {
      id: this.$route.params.id,
      visina: '',
      tezina: '',
      godiste: '',
      pol: '',
      phone: '',
      error: false,
      errorMessage: '',
      success: false,
      file: '',
      imagePreview: '',
      showPreview: false,
      selected: [],
      glutenFree: true,
      diabetes: true,
      heartDisease: true,
      highBloodPressure: true
    }
  },

  mounted() {
    AXIOS.get('users/mydata')
    .then(response => {
this.visina = response.data.userData.height;
this.godiste = response.data.userData.yearOfBirth;
this.tezina = response.data.userData.weight;
this.diabetes = response.data.userData.diabetes;
this.highBloodPressure = response.data.userData.highBloodPressure;
this.pol = response.data.userData.gender;
      console.log(response.data)
    })
  }

  ,
  methods: {
    add() {
      console.log(this.selected)

      const groceries = {
            'height': this.visina,
            'weight': this.tezina,
            'yearOfBirth': this.godiste,
            'gender': this.pol,
            'diabetes': this.diabetes,
            'heartDisease': this.heartDisease,
            'highBloodPressure': this.highBloodPressure,
            'allergies': this.selected
        }
        console.log(groceries)
        let i;
        for(i = 0; i < this.selected.length; i++)
            this.selected[i] = parseInt(this.selected[i])
        AXIOS.post('/users/data', groceries)
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
