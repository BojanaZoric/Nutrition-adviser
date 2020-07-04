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
         <li class="nav-item">
           <h5 class="nav-link"  v-on:click="redirectRecepies">Recepti</h5>
         </li>
         <li class="nav-item">
           <h5 class="nav-link"  v-on:click="redirectMeals">Moji obroci</h5>
         </li>
         <li class="nav-item">
           <h5 class="nav-link"  v-on:click="addGrocerie">Add grocerie</h5>
         </li>
          <li class="nav-item dropdown">
             <h5 class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
               Profil
             </h5>
             <div class="dropdown-menu" aria-labelledby="navbarDropdown">
               <h5 class="dropdown-item"  v-on:click="redirectProfile">Izmena profila</h5>
               <h5 class="dropdown-item" href="#">Odjavi me</h5>
             </div>
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
    addGrocerie() {
      this.$router.push("/add-grocerie")
    }

  }
}
</script>