<template>
<div>
  <div class="container-fluid pb-5">
    <div class="row header">
      <div class="col-md-7">
        <img src="../../../public/images/header1.jpg">
      </div>
      <div class="col-md-5">
        <div class="row r-card">
          <div class="col-md-7">
            <h2>Hard Cider-Braised Pork </h2>
            <br />
          </div>
          <div class="col-md-5">
            <img src="../../../public/images/r1.jpg">
          </div>
        </div>

        <div class="row r-card">
          <div class="col-md-7">
            <h2>Hard Cider-Braised Pork </h2>
          </div>
          <div class="col-md-5">
            <img src="../../../public/images/r2.jpg">
          </div>
        </div>

        <div class="row r-card">
            <div class="col-md-7">
              <h2>Hard Cider-Braised Pork </h2>
            </div>
            <div class="col-md-5">
              <img src="../../../public/images/r3.jpg">
            </div>
          </div>
      </div>
    </div>
  </div>
  <div class="container pt-5 pb-5">
    <h1>Najnoviji <span class="monsBold">recepti</span></h1>
  </div>
  <div class="row newest">
    <div class="card" style="width: 18rem;">
      <img class="card-img-top" src="../../../public/images/r3.jpg" alt="Card image cap">
      <div class="card-body">
        <h5>Hard Cider-Braised Pork </h5>
        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.
        <br />
        <span class="monsBold">11min</span> | Breakfast</p>
        <a href="#" class="card-link">More details</a>

      </div>
    </div>

    <div class="card" style="width: 18rem;">
      <img class="card-img-top" src="../../../public/images/r2.jpg" alt="Card image cap">
      <div class="card-body">
        <h5>Easy classic lasagne</h5>
        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.
        <br />
        <span class="monsBold">23min</span> | Desert</p>
        <a href="#" class="card-link">More details</a>

      </div>
    </div>
    <div class="card" style="width: 18rem;">
      <img class="card-img-top" src="../../../public/images/r1.jpg" alt="Card image cap">
      <div class="card-body">
        <h5>Thai fried prawn & pineapple rice</h5>
        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.
        <br />
        <span class="monsBold">21min</span> | Lunch</p>
        <a href="#" class="card-link">More details</a>

      </div>
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: 'homeContainer',
  data () {
    return {

    }
  },
  mounted(){

  }
  ,
  methods: {

  }
}
</script>
