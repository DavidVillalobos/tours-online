<template>
  <div id="app">
    <Navbar user=""/>
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

#app{
  background-color: #55567c
}

#search{
  margin: 3% 5% 0% 5%;
}

</style>
