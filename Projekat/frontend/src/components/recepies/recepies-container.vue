<template>
  <div class="container">
    <h1 class="mt-5">Dostupni <b>recepti</b> </h1>
    <div class="input-group mb-3 mt-5">
      <input type="text" class="form-control" placeholder="Pretrazite recepte..">
      <div class="input-group-append">
        <button class="btn btn-outline-secondary" type="button">Pretraga</button>
      </div>
    </div>

    <div class="row">
      <div class="form-group col-md-4">
        <label for="filter">Odaberite tip obroka</label>
        <select class="form-control" id="filter">
          <option>Doručak</option>
          <option>Ručak</option>
          <option>Večera</option>
        </select>
      </div>
      <div class="form-group col-md-4">
        <label for="filter">Odaberite tip ishrane</label>
        <select class="form-control" id="filter">
          <option>Vegan</option>
          <option>Vegeterijanac</option>
        </select>
      </div>
    </div>
    <recepiesList> </recepiesList>
  </div>
</template>

<script>
import recepiesList from '@/components/recepies/recepies-list'

export default {
  name: 'recepiesContainer',
  components: {
      recepiesList
    },
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
