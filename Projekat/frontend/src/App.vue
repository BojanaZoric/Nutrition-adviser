<template>
  <div id="app">
    <navbar></navbar>
    <router-view/>
  </div>
</template>

<script>
import NavbarComponent from '@/components/navbar.vue'
export default {
  name: 'App',
  components: {
    'navbar': NavbarComponent
  }
}
</script>