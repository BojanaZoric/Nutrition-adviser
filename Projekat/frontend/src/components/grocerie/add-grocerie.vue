<template>
  <div class="container pt-5 pb-5">
    <h1>Dostupni <span class="monsBold">obroci</span></h1>
 <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Naziv</th>
          <th scope="col">Instrukcija</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in meals" v-bind:key="item.id">
          <th scope="row"></th>
          <td>{{item.name}}</td>
          <td>    <button type="button" class="btn btn-primary" @click="editA(item.id)">Izmeni</button>
</td>
          <td>    <button type="button" class="btn btn-danger" @click="deleteA(item.id)">Obriši</button>
</td>


        </tr>
      </tbody>
    </table>

  </div>

</template>

<script>
import {AXIOS} from '../../http-common'

export default {
  name: 'addGrocerie',
  data () {
    return {
      meals: []
    }
  },
  mounted(){
    AXIOS.get(`http://localhost:8081/meal`)
    .then(response => {
      this.meals = response.data;
      console.log(this.meals)
    })
    .catch(e => {
      this.errors.push(e)
    })
  }
  ,
  methods: {
    deleteA(id) {
    AXIOS.delete(`http://localhost:8081/meal/` + id)
        .then(response => {
          this.meals = this.meals.filter(x => {
  return x.id != id;});
  console.log(response)

        })
        .catch(e => {
          this.errors.push(e)
        })
        }



, editA(id) {
        this.$router.push("/edit-meal/" + id)
}
}
}
</script>
