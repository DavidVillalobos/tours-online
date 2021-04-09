<template>
  <div id="app">
    <Navbar/>
    <SearchTour id="search"/>
  </div>
</template>

<script>
import Navbar from './components/NavBar'
import SearchTour from './components/SearchTour'

export default {
  name: 'App',
  components: {
    Navbar,
    SearchTour
  }
}
</script>

<style>
html, #app{
  background-image: url("../assets/background.png");
  background-size: cover;
  background-attachment: fixed;
  background-repeat: no-repeat;
  background-position: bottom;
}

#search{
  margin: 3% 3% 0% 3%;
}

</style>
