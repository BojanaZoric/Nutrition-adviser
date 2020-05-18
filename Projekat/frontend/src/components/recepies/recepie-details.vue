<template>
<div class="container pt-5 pb-5">
   <h1>Detalji <span class="monsBold">obroka</span></h1>
   <div class="row mt-5">
    <div class="col-md-3">
       <img class="card-img-left" src="../../../public/images/r1.jpg" alt="Card image cap">

    </div>
     <div class="col-md-9">
        <h2>{{recepie.name}}</h2>
        <p>{{recepie.description}}<br />
         <br />
         <br />
         <span class="monsBold">{{recepie.preparationTime}}min</span> | {{recepie.calories}}cal
         <br /></p>
        <h4>Potrebne namirnice </h4>
        <ul>
          <li v-for="grocerie in recepie.groceries">{{grocerie.grocerie.name}}, {{grocerie.quantity}}g </li>


        </ul>
        <br />

        <h4>Nutritivna vrednost</h4>
        Broj kalorija: <span class="monsBold">{{recepie.calories}}cal</span><br />
        Količina proteina: <span class="monsBold">{{recepie.proteinAmount}}g</span> <br />
        Količina ugljenih-hidrata: <span class="monsBold">{{recepie.carbohydrateAmount}}g</span>

        <h4 class="mt-3">Način pripreme</h4>
        <p>{{recepie.instructions}}</p>
      </div>

   </div>
</div>
</template>

<script>

import {AXIOS} from '../../http-common'

export default {
  name: 'recepieDetails',
  data () {
    return {
      recepie: {
        name: "",
        description: "",
        preparationTime: 0,
        calories: "",


      },
    }
  },
  mounted(){

    let api = 'meal/' +  1
    AXIOS.get(api)
      .then(response => {
        this.recepie = response.data[0]
        console.log(this.recepie)
        })
      .catch(err => console.log(err))
  }
  ,
  methods: {

  }
}
</script>
