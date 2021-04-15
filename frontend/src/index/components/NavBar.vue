<template>
  <div>
    <b-navbar type="dark" variant="dark">
      <b-navbar-brand>
        <h3>
        <b-icon-flag-fill variant="warning" rotate="-10" ></b-icon-flag-fill> 
        Get Your Tour
        </h3>
      </b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="mr-auto">
          <b-nav-item v-if="isAdmin" variant="dark" @click="gotoAdmin">
            <b-icon icon="file-earmark-plus-fill"></b-icon>
            Nuevo tour
          </b-nav-item>
          <b-nav-item variant="dark" @click="gotoHome">
            <b-icon icon="search"></b-icon>
            Busqueda de tours
          </b-nav-item>
           <b-nav-item v-show="username !== ''" variant="dark" @click="gotoShop">
            <b-icon icon="cart"></b-icon>
            Carro
          </b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto">
          <template v-if="username !== ''">
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
            
            <b-nav-item @click="gotoShop">
              <b-icon-cart scale="2.5"></b-icon-cart>
              <h5 class="items-cart"> {{ cartItems }} </h5>
            </b-nav-item>
          </template>
          <template v-else>
            <b-nav-item v-b-modal.modal-login>
              <b-icon-person-lines-fill></b-icon-person-lines-fill>
              Iniciar Sesion
            </b-nav-item>
            <b-nav-item v-b-modal.modal-register>
              <b-icon-person-plus-fill></b-icon-person-plus-fill>
              Registrarse
            </b-nav-item>
          </template>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <b-alert
      dismissible
      :variant="alertvariant"
      fade
      :show="showAlert"
      @dismissed="showAlert=false"
      @dismiss-count-down="countDownChanged">
      <h5>
        <b-icon icon="exclamation-circle-fill" variant=alertvariant> </b-icon>
        {{ messageAlert }}
      </h5>
    </b-alert>
    <b-modal id="modal-login" hide-header body-bg-variant="dark" footer-bg-variant="dark"
      @show="resetModal" @hidden="resetModal" @ok="login" footer-border-variant="dark"
      cancel-title="Cancelar" ok-title="Iniciar">
      <h3 class="text-center text-light">
        <b-icon-person-circle></b-icon-person-circle>
        Inicio de sesion <br><br>
      </h3>
    <b-form-group>
      <b-form-input placeholder="Email" :state="user.email != ''" v-model="user.email"></b-form-input><br>
      <b-form-input type="password" :state="user.password != ''" placeholder="Contraseña" v-model="user.password"></b-form-input>
    </b-form-group>
    </b-modal>
     <b-modal id="modal-register" hide-header body-bg-variant="dark" footer-bg-variant="dark"
      @show="resetModal" @hidden="resetModal" @ok="register" footer-border-variant="dark"
      cancel-title="Cancelar" ok-title="Registrar">
      <h3 class="text-center text-light">
        <b-icon-person-plus-fill></b-icon-person-plus-fill>
        Registro de usuario <br><br>
      </h3>
      <b-form-group>
        <b-form-input placeholder="Identificacion" v-model="user.identification" :state="user.identification != ''"></b-form-input><br>
        <b-form-input placeholder="Nombre" v-model="user.name" :state="user.name != ''"></b-form-input><br>
        <b-form-input placeholder="Apellido" v-model="user.lastName" :state="user.lastName != ''"></b-form-input><br>
        <b-form-input placeholder="Email" v-model="user.email" :state="user.email != ''"></b-form-input><br>
        <b-form-input type="password" placeholder="Password" v-model="user.password" :state="user.password != ''"></b-form-input>
        <b-form-datepicker dark label-help="" :state="user.birthday!=''" 
                    placeholder="Fecha Nacimiento" hide-header 
                    v-model="user.birthday" :max="max"  
                    :date-format-options="{ year: 'numeric', month: 'numeric', day: 'numeric' }"
                    locale="es" class="mt-3"></b-form-datepicker>
        <b-row class="mt-1">
          <b-col class="text-light text-center">
            <h4>Pais</h4>
          </b-col>
        </b-row>
        <b-row class="mt-1">
          <b-col>
          <b-form-select v-model="user.country" :state="user.country!=null" :options="countries"></b-form-select>
          </b-col>
          <b-col>
            <b-button block variant="warning" v-b-modal.modal-countryUser>
              Agregar país
            </b-button>
          </b-col>
        </b-row>
      </b-form-group>
    </b-modal>
    <b-modal id="modal-countryUser" hide-header body-bg-variant="dark" footer-bg-variant="dark"
        @show="resetModal" @hidden="resetModal" @ok="createNewCountry" footer-border-variant="dark"
        cancel-title="Cancelar" ok-title="Agregar">
        <h3 class="text-center text-light">
          <b-icon-map-fill></b-icon-map-fill>
          Agregar nuevo pais <br><br>
        </h3>
      <b-form-group>
        <b-form-input placeholder="Pais" v-model="newCountry" :state="newCountry != ''"></b-form-input><br>
      </b-form-group>
    </b-modal>
  </div>
</template>

<script>
export default {
  name: 'Navbar',
  data(){
    const maxDate = new Date()
    if(!this.$session.exists()){ this.$session.start() }
    if(!this.$session.has('cart')){ this.$session.set('cart', []);}
    return {
      countries: [],
      max: maxDate,
      newCountry: '',
      user: {
        email: "",
        password: "",
        name: "",
        lastName: "",
        identification: "",
        country: null,
        birthday: new Date(),
        admin: 0 // Admin permissions
      },
      messageAlert: "",
      showAlert: 0,
      alertvariant: "",
      secShowAlert: 4,
      cartItems: this.$session.get("cart").length,
    }
  },
  async created(){
    const response = await fetch('http://localhost:8001/countries', {method: 'GET'});
    let countriesFetch = (await response.json());
    countriesFetch.forEach(country => {
      this.countries.push({text:country.name, value:country})
    });
  },
  computed: {
    username(){
      if(this.$session.has('user')){
        let user = this.$session.get('user')
        return user['name'] + ' ' + user['lastName'];
      }
      return '';
    },
    avatarname(){
      if(this.$session.has('user')){
        let user = this.$session.get('user')
        return user['name'][0] + user['lastName'][0];
      }
      return '';
    },
    isAdmin(){
      if(this.$session.has('user')){
        let user = this.$session.get('user')
        return user['admin'];
      }
      return false;
    }
  },
  methods: {
    resetModal(){
      this.user.email = '';
      this.user.password = '';
      this.user.name = '';
      this.user.lastName = '';
      this.user.identification = '';
      this.user.country = null;
      this.user.birthday = new Date();
      this.newCountry = '';
    },
    countDownChanged(dismissCountDown) {
        this.showAlert = dismissCountDown
    },
    async login(){
        try {
          if(!this.user.email || !this.user.password){
            this.messageAlert = "Debe ingresar el email y la contraseña";
            this.alertvariant = "danger";
            this.showAlert = this.secShowAlert;
            return;
          }
          this.$emit("updateOverlay", true);
          const response = await fetch('http://localhost:8001/user/authenticate', {
            method: 'POST',
            headers: { "Content-Type": "application/json"},
            body: JSON.stringify(this.user)
          });
          this.$emit("updateOverlay", false);
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
          if(!this.$session.get('tour') || window.location.href != 'http://localhost:8002/'){
            document.location.reload();
          }
        } catch (err) {
          this.$emit("updateOverlay", false);
          this.messageAlert = "El servidor no responde";
          this.alertvariant = "danger";
        }
        this.showAlert = this.secShowAlert;
    },
    logout(){
      this.$session.remove('user');
      this.resetModal()
      if(window.location.href == 'http://localhost:8002/admin'){
        window.location.href = 'http://localhost:8002/'
      }else {
        document.location.reload();
      }
    },
    async register(){
      try {
        if( !this.user.email || !this.user.password || 
            !this.user.name || !this.user.lastName || 
            !this.user.identification || !this.user.country || 
            !this.user.birthday){
          this.messageAlert = "Debe ingresar el Id, Nombre, Apellido, email, contraseña, fecha de nacimiento y pais";
          this.alertvariant = "danger";
          this.showAlert = this.secShowAlert;
          return;
        }
        if(this.user.password.length < 8 || !/\d/.test(this.user.password) || 
          !/[a-z]/.test(this.user.password) || !/[A-Z]/.test(this.user.password) || 
          !/\W/.test(this.user.password))
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
          headers: { "Content-Type": "application/json"},
          body: JSON.stringify(this.user)
        });
        if(response.status == 404){
          this.messageAlert = "Registro fallido, ya existe un usuario con esa identificación o email";
          this.alertvariant = "danger";
          this.showAlert = this.secShowAlert;
          return;
        }
        this.messageAlert = "Registro exitoso, proceda a iniciar sesion";
        this.alertvariant = "success";
      } catch (err) {
        this.messageAlert = "El servidor no responde";
        this.alertvariant = "danger";
      }
      this.showAlert = this.secShowAlert;
    },
    async createNewCountry(){
      if(this.newCountry == ''){
          this.messageAlert = "Debes ingresar el nombre del pais";
          this.alertvariant = "warning";
          this.showAlert = this.secShowAlert;
          return;
      }
      try {
        this.$emit("updateOverlay", true);
        const response = await fetch('http://localhost:8001/country', {
          method: 'POST',
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            name: this.newCountry
          })
        });
        this.$emit("updateOverlay", false);
        if(response.status == 404){
          this.messageAlert = "Registro fallido, ya existe este pais";
          this.alertvariant = "danger";
          this.showAlert = this.secShowAlert;
          return;
        }
        this.messageAlert = "Registro de pais exitoso";
        this.alertvariant = "success";
        this.updateCountryCities()
      } catch (err) {
        console.log(err)
        this.$emit("updateOverlay", false);
        this.messageAlert = "El servidor no responde";
        this.alertvariant = "danger";
      }
      this.showAlert = this.secShowAlert;
    },
    async updateCountryCities(){
      this.countries = [];
      const response = await fetch('http://localhost:8001/countries', {method: 'GET'});
      let countriesFetch = (await response.json());
      countriesFetch.forEach(country => {
        this.countries.push({text:country.name, value:country})
      });
    },
    gotoShop(){
      if(this.cartItems == 0){
        this.messageAlert = "El carro esta vacio, agregue algun tour primero";
        this.alertvariant = "warning";
        this.showAlert = this.secShowAlert;
        return;
      }
      window.location.href = 'http://localhost:8002/shop';
    },
    gotoHome(){
      if(this.$session.has('tour')){
        this.$session.remove('tour')
      }
      window.location.href = 'http://localhost:8002/';
    },
    gotoAdmin(){
      if(this.$session.has('tour')){
        this.$session.remove('tour')
      }
      window.location.href = 'http://localhost:8002/admin';
    }
  }
}

</script>

<style scoped>

.items-cart{
  position: absolute; 
  z-index: 1;
  margin-left: 6px;
  margin-top: -27px;
}

</style>
