<template>
<div class="container">
  <div class="row mt-5 recepie-card"   v-for="meal in meals" :key="meal.id">
    <div class="col-md-1">
    </div>
    <div class="col-md-9">
      <h3>{{meal.name}}</h3>
      <p> {{meal.description}}<br />
       <span class="monsBold mt-2">{{meal.preparationTime}}min</span> <br /></p>
       <a href="#" class="card-link" @click="addMeal(meal.id)">More details</a>

    </div>
  </div>
</div>
</template>

<script>

export default {
  name: 'recepiesList',
  props: ['meals'],
  data () {
    return {

    }
  },
  mounted(){
    console.log(this.meals);
  }
  ,
  methods: {
      addMeal(id) {
      this.$router.push("/details/" + id)
    }
  }
}
</script>
