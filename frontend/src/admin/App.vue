<template>
  <div id="app">
    <Navbar/>
    <CreateTour id="create-tour"/>
  </div>
</template>

<script>
import Navbar from '../index/components/NavBar'
import CreateTour from './components/CreateTour'

export default {
  name: 'App',
  components: {
    CreateTour,
    Navbar
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

#create-tour{
  margin: 3% 3% 0% 3%;
}

</style>
