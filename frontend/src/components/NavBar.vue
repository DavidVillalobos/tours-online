<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand>
        <h3>
        <b-icon-map-fill variant="warning" rotate="-10"></b-icon-map-fill> 
        GetYourTour
        </h3>
      </b-navbar-brand>
      <template v-if="username !== ''">
        <b-navbar-nav class="ml-auto">
            <b-nav-item-dropdown right>
              <template #button-content>
                <em>{{ username }}</em>
              </template>
              <b-dropdown-item-button href="#">Perfil</b-dropdown-item-button>
              <b-dropdown-item-button v-on:click="logout">Cerrar Sesion</b-dropdown-item-button>
            </b-nav-item-dropdown>
        </b-navbar-nav>
        <b-avatar variant="warning">
          <strong> {{ avatarname }} </strong>
        </b-avatar>
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
  data(){
    return {
      username: "",
      avatarname: "",
      email: "",
      password: ""
    }
  },
  methods: {
    resetModal(){
      this.email = ''
      this.password = ''
    },
    async login(){
        try {
          const response = await fetch('http://localhost:8001/user/authenticate', {
            method: 'POST',
            headers: {
              "Content-Type": "application/json"
            },
            body: JSON.stringify({
              email: this.email,
              password: this.password
            })
          });
          const user = (await response.json());
          if(!this.$session.exists()){
            this.$session.start()
          }
          this.$session.set('user', user);
          this.username = user['name'] + ' ' + user['lastName'];
          this.avatarname = user['name'][0] + user['lastName'][0];
        } catch (err) {
          alert("Login failed")
        }
    },
    logout(){
      this.$session.remove('user');
      this.username = '';
      this.avatarname = ''
    }
  }
}

</script>

<style scoped>
</style>
