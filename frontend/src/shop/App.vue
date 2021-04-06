<template>
  <div id="app">
    <Navbar/>
  </div>
</template>

<script>
import Navbar from './components/NavBar'

export default {
  name: 'App',
  components: {
    Navbar
  }
}
</script>

<style>
html, #app{
  background-image: url("./assets/background.png");
  background-size: cover;
  background-attachment: fixed;
  background-repeat: no-repeat;
  background-position: bottom;
}

#search{
  margin: 3% 3% 0% 3%;
}

</style>
