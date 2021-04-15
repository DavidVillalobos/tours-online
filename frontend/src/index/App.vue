<template>
  <div id="app">
     <b-overlay id="overlay" variant="dark" :show="show">
      <Navbar v-on:updateOverlay="updateOverlay"/>
      <SearchTour id="search" v-on:updateOverlay="updateOverlay"/>
     <template #overlay>
        <div class="text-center text-light mt-4">
          <b-icon icon="clock" font-scale="6" animation="spin"></b-icon>
          <h2>Por favor espere . . . </h2>
        </div>
    </template>
    </b-overlay>
  </div>
</template>

<script>
import Navbar from './components/NavBar'
import SearchTour from './components/SearchTour'

export default {
  name: 'App',
  data(){
    return {
      show: false
    }
  },
  methods:{
    updateOverlay(newShow){
      this.show = newShow; 
    }
  },
  components: {
    Navbar,
    SearchTour
  }
}
</script>

<style>

html, body, #app {
  background-image: url("../assets/background.png");
  background-size: cover;
  background-attachment: fixed;
  background-repeat: no-repeat;
  background-position: bottom;
  margin: 0;
  padding: 0;
  height: 100%;
}

#search{
  margin: 3% 3% 0% 3%;
}

#overlay{
  min-height: 100%;
}

</style>
