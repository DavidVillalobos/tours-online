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
        <b-button variant="dark" v-b-modal.modal-login>
          <b-icon-person-fill variant="light"></b-icon-person-fill>
          Iniciar Sesion
        </b-button>
        <b-button variant="dark" v-b-modal.modal-register>
          Registrarse
        </b-button>
      </b-navbar-nav>
      </template>
    </b-navbar>
    <b-modal id="modal-login" hide-header
      @show="resetModal" @hidden="resetModal" @ok="login"
      cancel-title="Cancelar" ok-title="Iniciar"
    >
    <div class="d-block text-center">
      <h3>
        <b-icon-person-fill variant="dark"></b-icon-person-fill>
        Iniciar Sesion
      </h3>
    </div>
    <b-form-group label-cols="4" label-cols-lg="2" label="Email">
      <b-form-input v-model="email"></b-form-input>
    </b-form-group>
    <b-form-group label-cols="4" label-cols-lg="2" label="contraseña">
      <b-form-input v-model="password"></b-form-input>
    </b-form-group>
    </b-modal>
  </div>
</template>

<script>
export default {
  name: 'Navbar',
  props: {
    user: String,
    email: String,
    password: String,
  },
  methods: {
    avatarName() {
      var result = ''
      for(let word of this.user.split(' ')){
        result += word[0];
      }
      return result;
    },
    resetModal(){
      this.email = ''
      this.password = ''
    },
    login(){
      alert("Login email: " + this.email + "  password: " + this.password)
    }
  }
}

</script>

<style scoped>
</style>
