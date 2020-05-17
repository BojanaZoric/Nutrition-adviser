<template>
  <div class="container pt-5 pb-5">
    <h1>Korisnički <span class="monsBold">profil</span></h1>

    <form>
      <div class="form-group">
        <label for="visina">Visina</label>
        <input type="text" class="form-control" id="visina" placeholder="Visina">
      </div>
      <div class="form-group">
        <label for="tezina">Tezina</label>
        <input type="text" class="form-control" id="tezina" placeholder="Tezina">
      </div>

       <div class="form-group">
          <label for="godiste">Godište</label>
          <input type="text" class="form-control" id="godiste" placeholder="Godište">
        </div>

         <div class="form-group">
            <label for="pol">Pol</label>
            <select class="form-control" id="pol">
              <option>Muški</option>
              <option>Ženski</option>
            </select>
          </div>

          <div class="form-group">
            <label for="tip_ishrane">Tip ishrane</label>
            <select class="form-control" id="tip_ishrane">
              <option>Vegeterijanac</option>
              <option>Vegan</option>
            </select>
          </div>

          <div class="form-group">
            <label for="alergije">Alergije na hranu</label>
            <select multiple class="form-control" id="alergije">
              <option>Jaja</option>
              <option>Mleko</option>
              <option>Gluten</option>
              <option>Orašasti plodovi</option>
            </select>
          </div>

          <div class="form-group">
            <label for="problemi">Zdravstveni problemi</label>
            <select multiple class="form-control" id="problemi">
              <option>Dijabetes</option>
              <option>Visok pritisak</option>
              <option>Bolest srca</option>
            </select>
          </div>

          <button type="button" class="btn btn-primary">Sačuvaj izmene</button>

    </form>
  </div>

</template>

<script>
export default {
  name: 'editProfile',
  data () {
    return {
      id: this.$route.params.id,
      username: '',
      firstName: '',
      lastName: '',
      city: '',
      phone: '',
      error: false,
      errorMessage: '',
      success: false,
      file: '',
      imagePreview: '',
      showPreview: false
    }
  },
  mounted(){

  }
  ,
  methods: {

  }
}
</script>
