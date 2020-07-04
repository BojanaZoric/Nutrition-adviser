<template>
<div class="container">
  <div class="row mt-5 recepie-card">
    <div class="col-md-3">
      <img class="card-img-left" src="../../../public/images/r3.jpg" alt="Card image cap">
    </div>
    <div class="col-md-9">
      <h3>Thai fried prawn & pineapple rice</h3>
      <p> Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam. <br />
       <span class="monsBold mt-2">11min</span> | Breakfast<br /></p>
       <a href="#" class="card-link">More details</a>

    </div>
  </div>

  <div class="row mt-5 recepie-card">
    <div class="col-md-3">
      <img class="card-img-left" src="../../../public/images/r1.jpg" alt="Card image cap">
    </div>
    <div class="col-md-9">
      <h3>Hard Cider-Braised Pork </h3>
      <p> Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam. <br />
       <span class="monsBold mt-2">11min</span> | Breakfast<br /></p>
       <a href="#" class="card-link">More details</a>

    </div>
  </div>

  <div class="row mt-5 recepie-card">
      <div class="col-md-3">
        <img class="card-img-left" src="../../../public/images/r2.jpg" alt="Card image cap">
      </div>
      <div class="col-md-9">
        <h3>Easy classic lasagne </h3>
        <p> Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam. <br />
         <span class="monsBold mt-2">11min</span> | Breakfast<br /></p>
         <a href="#" class="card-link">More details</a>

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

  }
}
</script>
