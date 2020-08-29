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

         <li class="nav-item dropdown">
             <h5 class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
               Recepti
             </h5>
             <div class="dropdown-menu" aria-labelledby="navbarDropdown">
               <h5 class="nav-link"  v-on:click="redirectRecepies">Svi recepti</h5>
                <h5 class="nav-link"  v-on:click="redirectFindMeal">Pronadji obrok</h5>
             </div>
           </li>

         <li class="nav-item">
           <h5 class="nav-link"  v-on:click="redirectMeals">Moji obroci</h5>
         </li>
         <li class="nav-item">
           <h5 class="nav-link"  v-on:click="addMeal">Dodaj obrok</h5>
         </li>

         <li class="nav-item">
           <h5 class="nav-link"  v-on:click="addGrocerie">Dodaj namirnicu</h5>
         </li>

         <li class="nav-item">
           <h5 class="nav-link"  v-on:click="login">Login</h5>
         </li>
       </ul>

   </div>
 </nav>
</template>

<script>
export default {
  name: 'navbar-component',
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
      this.$router.push("/add-grocerie")
    },

    addMeal() {
      this.$router.push("/add-meal")
    },
    login() {
      this.$router.push("/login")
    }

  }
}
</script>
