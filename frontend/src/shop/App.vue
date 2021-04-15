<template>
  <div id="app">
     <b-overlay id="overlay" variant="dark" :show="show">
      <Navbar v-on:updateOverlay="updateOverlay"/>
      <CartShopping v-on:updateOverlay="updateOverlay"/>
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
import Navbar from '../index/components/NavBar'
import CartShopping from './components/CartShopping'

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
    CartShopping
  }
}
</script>

<style>

html,body, #app{
  background-image: url("../assets/background.png");
  background-size: cover;
  background-attachment: fixed;
  background-repeat: no-repeat;
  background-position: bottom;
  margin: 0;
  padding: 0;
  height: 100%;
}

#overlay{
  min-height: 100%;
}

</style>
