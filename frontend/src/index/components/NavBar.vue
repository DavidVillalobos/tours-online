<template>
  <div>
    <b-alert
      class="alert-content" 
      dismissible
      :variant="alertvariant"
      fade
      :show="showAlert"
      @dismissed="showAlert=false"
      @dismiss-count-down="countDownChanged"
    >
      {{messageAlert }}
      <template v-if="messageAlert == 'Por favor espere . . .'">
        <b-icon icon="clock" font-scale="2" animation="spin"></b-icon>
      </template>
    </b-alert>
    <b-navbar type="dark" variant="dark">
      <b-navbar-brand>
        <h3>
        <b-icon-flag-fill variant="warning" rotate="-10" ></b-icon-flag-fill> 
        Get Your Tour
        </h3>
      </b-navbar-brand>
      <template v-if="username !== ''">
        <b-navbar-nav class="ml-auto">
            <b-nav-item disabled >Bienvenido</b-nav-item>
            <b-nav-item-dropdown right>
              <template #button-content>
                <em>
                  {{ username }}
                </em>
              </template>
              <b-dropdown-item-button v-on:click="logout">
                <b-icon icon="box-arrow-in-left" aria-hidden="true"></b-icon>
                <em>
                  Cerrar sesion
                </em>
              </b-dropdown-item-button>
            </b-nav-item-dropdown>            
        </b-navbar-nav>
        <b-avatar variant="warning">
          <strong> {{ avatarname }} </strong>
        </b-avatar>
        <b-button size="lg" variant="dark" @click="gotoShop">
          <b-icon-cart scale="2"></b-icon-cart>
          <h5 class="items-cart"> {{ cartItems }} </h5>
        </b-button>
      </template>
      <template v-else>
      <b-navbar-nav class="ml-auto">
        <b-button variant="dark" v-b-modal.modal-login>
          <b-icon-person-lines-fill variant="light"></b-icon-person-lines-fill>
          Iniciar Sesion
        </b-button>
        <b-button variant="dark" v-b-modal.modal-register>
          <b-icon-person-plus-fill variant="light"></b-icon-person-plus-fill>
          Registrarse
        </b-button>
      </b-navbar-nav>
      </template>
    </b-navbar>
    <b-modal id="modal-login" hide-header body-bg-variant="dark" footer-bg-variant="dark"
      @show="resetModal" @hidden="resetModal" @ok="login" footer-border-variant="dark"
      cancel-title="Cancelar" ok-title="Iniciar"
    >
      <h3 class="text-center text-light">
        <b-icon-person-circle></b-icon-person-circle>
        Inicio de sesion <br><br>
      </h3>
    <b-form-group>
      <b-form-input placeholder="Email" v-model="email"></b-form-input><br>
      <b-form-input placeholder="Contraseña" v-model="password"></b-form-input>
    </b-form-group>
    </b-modal>
     <b-modal id="modal-register" hide-header body-bg-variant="dark" footer-bg-variant="dark"
      @show="resetModal" @hidden="resetModal" @ok="register" footer-border-variant="dark"
      cancel-title="Cancelar" ok-title="Registrar" 
    >
      <h3 class="text-center text-light">
        <b-icon-person-plus-fill></b-icon-person-plus-fill>
        Registro de usuario <br><br>
      </h3>
    <b-form-group>
      <b-form-input placeholder="Identificacion" v-model="id"></b-form-input><br>
      <b-form-input placeholder="Nombre" v-model="name"></b-form-input><br>
      <b-form-input placeholder="Apellido" v-model="lastName"></b-form-input><br>
      <b-form-input placeholder="Email" v-model="email"></b-form-input><br>
      <b-form-input placeholder="Password" v-model="password"></b-form-input>
    </b-form-group>
    </b-modal>
  </div>
</template>

<script>
export default {
  name: 'Navbar',
  data(){
    if(!this.$session.exists()){ this.$session.start() }
    if(!this.$session.get('cart')){
      this.$session.set('cart', []);
    }
    return {
      email: "",
      password: "",
      name: "",
      lastName: "",
      id: "",
      messageAlert: "",
      showAlert: 0,
      alertvariant: "",
      secShowAlert: 4,
    }
  },
  computed: {
    cartItems(){
      return this.$session.get("cart").length;
    },
    username(){
      if(this.$session.get('user')){
        let user = this.$session.get('user')
        return user['name'] + ' ' + user['lastName'];
      }
      return '';
    },
    avatarname(){
      if(this.$session.get('user')){
        let user = this.$session.get('user')
        return user['name'][0] + user['lastName'][0];
      }
      return '';
    }
  },
  methods: {
    resetModal(){
      this.email = ''
      this.password = ''
      this.name = ''
      this.lastName = ''
      this.id = ''
    },
    countDownChanged(dismissCountDown) {
        this.showAlert = dismissCountDown
    },
    async login(){
        try {
          if(!this.email || !this.password){
            this.messageAlert = "Debe ingresar el email y la contraseña";
            this.alertvariant = "danger";
            this.showAlert = this.secShowAlert;
            return;
          }
          this.messageAlert = "Por favor espere . . .";
          this.alertvariant = "info";
          this.showAlert = this.secShowAlert;
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
          if(response.status == 404){
            this.messageAlert = "Inicio de sesion fallido, compruebe sus credenciales";
            this.alertvariant = "danger";
            this.showAlert = this.secShowAlert;
            return;
          }
          const user = (await response.json());
          if(!this.$session.exists()){
            this.$session.start()
          }
          this.$session.set('user', user);
          this.messageAlert = "Inicio de sesion exitoso";
          this.alertvariant = "success";
          if(!this.$session.get('tour') && window.location.href != 'http://localhost:8002'){
            window.location.href = 'http://localhost:8002';
          }
        } catch (err) {
          this.messageAlert = "El servidor no responde";
          this.alertvariant = "danger";
        }
        this.showAlert = this.secShowAlert;
    },
    logout(){
      this.$session.remove('user');
      this.resetModal()
      document.location.reload();
    },
    async register(){
      try {
        if(!this.email || !this.password || !this.name || !this.lastName || !this.id){
          this.messageAlert = "Debe ingresar el Id, Nombre, Apellido, email y contraseña";
          this.alertvariant = "danger";
          this.showAlert = this.secShowAlert;
          return;
        }
        if(this.password.length != 8 || /\d/.test(this.password) || 
          /[a-z]/.test(this.password) || /[A-Z]/.test(this.password) || 
          /\W/.test(this.password))
        {
          this.messageAlert = "La contraseña debe contener al menos: 8 Caracteres, un Numero, un Caracter Especial, una Mayúscula y una Minúscula";
          this.alertvariant = "warning";
          this.showAlert = this.secShowAlert * 2;
          return;
        }
        this.messageAlert = "Por favor espere . . .";
        this.alertvariant = "info";
        this.showAlert = this.secShowAlert;
        const response = await fetch('http://localhost:8001/user', {
          method: 'POST',
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            name: this.name,
            lastName: this.lastName,
            email: this.email,
            password: this.password,
            identification: this.id,
            country: { id : 1},
            birthday: new Date(),
            admin: 0
          })
        });
        if(response.status == 404){
          this.messageAlert = "Registro fallido";
          this.alertvariant = "danger";
          this.showAlert = this.secShowAlert;
          return;
        }
        this.messageAlert = "Registro exitoso, proceda a iniciar sesion";
        this.alertvariant = "success";
        document.location.reload();
      } catch (err) {
        this.messageAlert = "El servidor no responde";
        this.alertvariant = "danger";
      }
      this.showAlert = this.secShowAlert;
    },
    gotoShop(){
      if(this.cartItems == 0){
        this.messageAlert = "El carrito esta vacio, agregue algun tour primero";
        this.alertvariant = "warning";
        this.showAlert = this.secShowAlert;
        return;
      }
      window.location.href = 'http://localhost:8002/shop';
    }
  }
}

</script>

<style scoped>
.alert-content{
  position:absolute; z-index:1;
  margin-left: 40%;
  margin-top: 1%;
}

.items-cart{
  position: absolute; 
  z-index: 1;
  margin-left: 9px;
  margin-top: -30px;
}

</style>
