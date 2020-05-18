<template>
<div class="container">
  <div class="row mt-5 recepie-card"  v-for="recepie in recepies">
    <div class="col-md-3">
      <img class="card-img-left" src="../../../public/images/r3.jpg" alt="Card image cap">
    </div>
    <div class="col-md-9">
      <h3>{{recepie.name}}</h3>
      <p> {{recepie.description}} <br />
       <span class="monsBold mt-2">{{recepie.preparationTime}}min</span> | {{recepie.mealType.mealType }}<br /></p>
       <a href="#" class="card-link">More details</a>

    </div>
  </div>
</div>
</template>

<script>
import {AXIOS} from '../../http-common'

export default {
  name: 'recepiesList',
  data () {
    return {
      recepies: []
    }
  },
  mounted(){
    let api = 'meal/'
      AXIOS.get(api)
        .then(response => {
          this.recepies = response.data
          console.log(this.recepies)
          })
        .catch(err => console.log(err))

  }
  ,
  methods: {

  }
}
</script>
