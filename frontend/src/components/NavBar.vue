<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand>
        <h3>
        <b-icon-map-fill variant="warning" rotate="-10"></b-icon-map-fill> 
        GetYourTour
        </h3>
      </b-navbar-brand>
      <template v-if="user !== ''">
        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item-dropdown right>
              <template #button-content>
                <em>{{ user }}</em>
              </template>
              <b-dropdown-item href="#">Perfil</b-dropdown-item>
              <b-dropdown-item href="#">Cerrar Sesion</b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>
        <b-avatar variant="warning">
          <strong> {{ avatarName() }} </strong>
        </b-avatar>
        </b-collapse>
      </template>
      <template v-else>
      <b-navbar-nav class="ml-auto">
        <b-nav-item href="#">Iniciar Sesion</b-nav-item>
      </b-navbar-nav>
      <b-avatar variant="warning"></b-avatar>
      </template>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: 'Navbar',
  props: {
    user: String
  },
  methods: {
    avatarName: function(){
      var result = ''
      for(let word of this.user.split(' ')){
        result += word[0];
      }
      return result;
    }
  }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
