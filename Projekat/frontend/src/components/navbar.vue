<template>
  <nav class="navbar navbar-inverse navigation navbar-fixed-top" style="background-color: #2d3e50;!important">
   <div class="container">
     <div class="navbar-header">
       <h1 class="navbar-brand" href="#" v-on:click="redirect">|||Nutrition<span class="monsBold">Adviser</span></h1>
     </div>
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
    }

  }
}
</script>