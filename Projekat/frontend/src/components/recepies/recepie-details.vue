<template>
<div class="container pt-5 pb-5">
   <h1>Detalji <span class="monsBold">obroka</span></h1>
   <div class="row mt-5">
    <div class="col-md-3">
       <img class="card-img-left" src="../../../public/images/r1.jpg" alt="Card image cap">

    </div>
     <div class="col-md-9">
        <h2>{{meal.name}}</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam. <br />
         <br />
         <br />
         <span class="monsBold">15min</span>| 754cal
         <br /></p>
        <h4>Potrebne namirnice </h4>
        <ul>
          <li>Belo meso </li>
          <li>Pavlaka </li>
          <li>Krastavac </li>
          <li>Hleb </li>

        </ul>
        <br />

        <h4>Nutritivna vrednost</h4>
        Broj kalorija: <span class="monsBold">754cal</span><br />
        Količina proteina: <br />
        Količina ugljenih-hidrata:

        <h4 class="mt-3">Način pripreme</h4>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad min. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minUt enim ad minim veniam. <br /> <br /></p>

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
    axios.get(`http://localhost:8081/meal/1`)
    .then(response => {
      this.meal = response.data;
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
