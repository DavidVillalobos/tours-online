<template>
  <div id="app">
    <Navbar user=""/>
    <SearchTour user="" id="search"/>
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

#search{
  margin: 3% 5% 0% 5%;
}
</style>
