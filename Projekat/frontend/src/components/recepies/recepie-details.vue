<template>
<div class="container pt-5 pb-5">
   <h1>Detalji <span class="monsBold">obroka</span></h1>
   <div class="row mt-5">
    <div class="col-md-3">

    </div>
     <div class="col-md-9">
        <h2>{{meal.name}}</h2>
        <p>{{meal.description}}<br />
         <br />
         <br />
         <span class="monsBold">{{meal.preparationTime}}min</span>| {{meal.calories}}cal
         <br /></p>
        <h4>Potrebne namirnice </h4>
        <ul  v-for="grocerie in meal.groceries" :key="grocerie.grocerie.id">
          <li>{{grocerie.grocerie.name}}, {{grocerie.quantity}} {{grocerie.grocerie.measure}}</li>

        </ul>
        <br />

        <h4>Nutritivna vrednost</h4>
        Broj kalorija: <span class="monsBold">{{meal.calories}}cal</span><br />
        Količina proteina: {{meal.proteinAmount}}<br />
        Količina ugljenih-hidrata: {{meal.carbohydrateAmount}}

        <h4 class="mt-3" v-if="meal.instructions">Način pripreme</h4>
        <p>{{meal.instructions}}<br /> <br /></p>

        <div v-for="item in meal.steps" v-bind:key="item.name">
          <h5>{{item.name}}</h5>
          <p>{{item.instruction}}</p>
        </div>
      </div>

   </div>
</div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'recepieDetails',
  data () {
    return {
      meal: {}
    }
  },
  mounted(){
    axios.get(`http://localhost:8081/meal/` +  this.$route.params.id)
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
  }
  ,
  methods: {

  }
}
</script>
