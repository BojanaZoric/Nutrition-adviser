<template>
  <div class="container">

    <recepiesList :meals="meals"> </recepiesList>
  </div>
</template>

<script>
import recepiesList from '@/components/recepies/recepies-list'
import axios from 'axios';

export default {
  name: 'recepiesContainer',
  components: {
      recepiesList
    },
  data () {
    return {
      meals: []
    }
  },
  mounted(){
    axios.get(`http://localhost:8081/meal`)
    .then(response => {
      this.meals = response.data;
      console.log(this.meals)
    })
    .catch(e => {
      this.errors.push(e)
    })
  }
  ,
  methods: {

  }
}
</script>
