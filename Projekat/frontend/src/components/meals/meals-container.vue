<template>
  <div class="container">
    <h1 class="mt-5">Izveštaj <b>dana</b> </h1>
    <p>Ukupan broj unetih kalorija danas: <b>1415cal</b> </br>
    Potreban broj kalorija za dnevni limit: <b>325cal</b></p>
    <h4 class="mt-5">Današnji obroci </h4>

    <div class="row">
      <div class="col-md-3 meal-card">
      <h4>Doručak </h4>  <br />
      Omlet sa slaninom <br />
      <span class="monsBold">564cal</span> | Doručak</p>

      </div>
      <div class="col-md-3 meal-card">
        <h4>Ručak </h4>  <br />
        Piletina sa kikirikijem <br />
        <span class="monsBold">874cal</span> | Ručak</p>

      </div>
      <div class="col-md-3 meal-card">
        <h4>Dezert </h4>  <br />
        Puding od čokolade <br />
        <span class="monsBold">464cal</span> | Dezert</p>
      </div>
    </div>

    <h1 class="mt-5">Predlozi <b>obroka</b> </h1>
    <div class="row newest ml-0 mb-5">
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
  name: 'mealsContainer',
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
