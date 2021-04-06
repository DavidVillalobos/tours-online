<template>
  <div id="app">
    <Navbar/>
    <Tour id="tour"/>
  </div>
</template>

<script>
import Navbar from './components/NavBar'
import Tour from './components/Tour'

export default {
  name: 'App',
  components: {
    Navbar,
    Tour
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

#tour{
  margin: 3% 3% 0% 3%;
}

</style>
