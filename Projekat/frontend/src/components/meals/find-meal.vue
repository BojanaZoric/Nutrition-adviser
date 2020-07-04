<template>
  <div class="container">
    <h1 class="mt-5">Dostupni <b>recepti</b> </h1>
    <div class="form-group">
        <label for="problemi">Odaberi namirnice</label>
        <select multiple class="form-control" id="problemi">
            <option v-for="grocerie in allGroceries" :key="grocerie.id">{{grocerie.name}}</option>
        </select>
    </div>
    <button type="button" class="btn btn-primary" @click="findAll">Pronadji recept sa svim namirnicama</button>

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
      allGroceries: []
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
          
      }

  }
}
</script>
