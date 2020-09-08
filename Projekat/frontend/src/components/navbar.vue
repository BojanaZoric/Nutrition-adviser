<template>
  <nav class="navbar navbar-inverse navbar-expand-lg  navigation navbar-fixed-top" style="background-color: #2d3e50;!important">
   <div class="container">
     <div class="navbar-header">
       <h1 class="navbar-brand" href="#" v-on:click="redirect">|||Nutrition<span class="monsBold">Adviser</span></h1>
     </div>

       <ul class="navbar-nav">
         <li class="nav-item active">
           <h5 class="nav-link"  v-on:click="redirect">Početna<span class="sr-only">(current)</span></h5>
         </li>

         <li class="nav-item dropdown" v-if="isUser">
             <h5 class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
               Recepti
             </h5>
             <div class="dropdown-menu" aria-labelledby="navbarDropdown">
               <h5 class="nav-link"  v-on:click="redirectRecepies">Svi recepti</h5>
                <h5 class="nav-link"  v-on:click="redirectFindMeal">Pronadji obrok</h5>
             </div>
           </li>

         <li class="nav-item">
           <h5 class="nav-link" v-if="isAdmin"  v-on:click="addMeal">Dodaj obrok</h5>
         </li>

         <li class="nav-item">
           <h5 class="nav-link" v-if="isAdmin"  v-on:click="addGrocerie">Obroci</h5>
         </li>

         <li class="nav-item">
          <h5 class="nav-link" v-if="isUser"  v-on:click="redirectProfile">Moj  profil</h5>
         </li>

         <li class="nav-item">
           <h5 class="nav-link"  v-if="!logged" v-on:click="login">Login</h5>
         </li>

         <li class="nav-item">
           <h5 class="nav-link"  v-if="logged" v-on:click="logout">Log out</h5>
         </li>
       </ul>

   </div>
 </nav>
</template>

<script>
import { AXIOS } from '../http-common';
export default {
  name: 'navbar-component',
  data () {
    return {
      logged: localStorage.getItem('token') !== 'null' ? true : false,
      isAdmin: localStorage.getItem('role') !== 'null' && localStorage.getItem('role') === 'ROLE_ADMIN' ? true : false,
      isUser: localStorage.getItem('role') !== 'null' && localStorage.getItem('role') === 'ROLE_USER' ? true : false,
    }
  },mounted: function () {
    console.log(localStorage.getItem('token'))
    if(localStorage.getItem('token') !== 'null')
    this.logged = true;
    else
    this.logged = false;

   console.log(localStorage.getItem('role'))
  AXIOS.get('users/author')
    .then(response => {
      if(response.data  === 'ROLE_ADMIN'){
        this.isAdmin = true;
        this.isUser=false;}
      else{
        this.isUser = true;
        this.isAdmin=false;
      }
    })

  },
  methods: {
    redirect() {
        if (localStorage.getItem('token') == null) {
          this.$router.push("/")
      }
    },


    redirectMeals() {
      this.$router.push("/meals")
    },

    redirectRecepies() {
        this.$router.push("/recepies")
      },

    redirectProfile() {
            this.$router.push("/edit-profile")
          },
    redirectFindMeal() {
      this.$router.push("/find-meal")
    },
    addGrocerie() {
      this.$router.push("/meals-table")
    },

    addMeal() {
      this.$router.push("/add-meal")
    },
    login() {
      this.$router.push("/login")
    }
    ,logout() {
                console.log(localStorage.getItem('token'))

       localStorage.setItem('token', null);
       console.log("lo");
               console.log(localStorage.getItem('token'))
      this.logged = false;
              this.isUser=false;
        this.isAdmin=false;

            this.$router.push("/")

    }

  }
}
</script>
