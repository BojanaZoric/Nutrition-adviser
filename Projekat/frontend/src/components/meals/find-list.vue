<template>
<div class="container">
  <div class="row mt-5 recepie-card"   v-for="meal in meals" :key="meal.id">
    <div class="col-md-3">
      <img class="card-img-left" src="../../../public/images/r3.jpg" alt="Card image cap">
    </div>
    <div class="col-md-9">
      <h3>{{meal.name}}</h3>
      <p> {{meal.description}}<br />
       <span class="monsBold mt-2">{{meal.preparationTime}}min</span> | {{meal.mealType.mealType}}<br /></p>
       <a href="#" class="card-link"  @click="findMissing(meal.id)">Missing groceries</a>

    </div>
  </div>
</div>
</template>

<script>

export default {
  name: 'findList',
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

  }
}
</script>
