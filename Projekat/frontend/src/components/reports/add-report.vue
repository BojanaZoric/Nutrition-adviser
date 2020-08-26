<template>
  <div class="container pt-5 pb-5">
    <h1>Dodavanje <span class="monsBold">namirnice</span></h1>

    <form>
      <div class="form-group">
        <label for="naziv">Naziv namirnice</label>
        <input type="text" class="form-control" id="naziv" placeholder="Naziv" v-model="name">
      </div>

        <label for="problemi">Namirnice</label>
        <select multiple class="form-control" id="problemi">
            <option v-for="grocerie in allGroceries" :key="grocerie.id">{{grocerie.name}}</option>
        </select>

    <br />
    <button type="button" class="btn btn-primary" @click="add">Dodaj namirnicu</button>

    </form>
  </div>

</template>

<script>
import {AXIOS} from '../../http-common'

export default {
  name: 'addReport',
  data () {
    return {
      userId: '',
      days: 0,
      allUsers: []
    }
  },
  mounted(){
      AXIOS.get('/users')
        .then(response => {
            this.allUsers = response.data 
            console.log(this.allUsers)
        })
        .catch(err => {
            console.log(err)
        })
  }
  ,
  methods: {
       add (e) {
        e.preventDefault()
        const report = {
            'userId': this.userId,
            'days': this.days
        }

        AXIOS.post('/reports', report)
        .then(response => {
            console.log(response)
        })
        .catch(err => {
            console.log(err)
        })
        }

  }
}
</script>
