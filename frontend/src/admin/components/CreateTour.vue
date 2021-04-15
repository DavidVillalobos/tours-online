<template>
  <div>
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
    <b-modal id="modal-country" hide-header body-bg-variant="dark" footer-bg-variant="dark"
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
    <b-modal id="modal-city" hide-header body-bg-variant="dark" footer-bg-variant="dark"
      @show="resetModal" @hidden="resetModal" @ok="createNewCity" footer-border-variant="dark"
      cancel-title="Cancelar" ok-title="Agregar">
      <h3 class="text-center text-light">
        <b-icon-map-fill></b-icon-map-fill>
        Agregar nueva ciudad <br><br>
      </h3>
    <b-form-group class="text-light">
      <b-row>
        Pais
        <b-form-select v-model="countrySelected" :options="countries" @change="loadCities"></b-form-select>
      </b-row>
      <b-row class="mt-3">
        <b-form-input placeholder="Ciudad" :state="newCity != ''" v-model="newCity"></b-form-input><br>
      </b-row>
    </b-form-group>
    </b-modal>
    <b-card id="card-admin" bg-variant="dark">
      <b-card bg-variant="light">
        <h2 class="text-center">
          <b-icon icon="map-fill"></b-icon>
          Registro de Tour
        </h2>
        <b-row>
          <b-col>
            <b-row>
              <b-col>
              Pais
              <b-form-select v-model="countrySelected" :state="countrySelected!=null" :options="countries" @change="loadCities"></b-form-select>
              </b-col>
              <b-col class="mt-4">
                <b-button block variant="secondary" v-b-modal.modal-country>
                  Agregar país
                </b-button>
              </b-col>
            </b-row>
            <b-row>
            <b-col>
              Ciudad
              <b-form-select v-model="citySelected" :state="citySelected!=null" :options="cities" @change="includeCity"></b-form-select>
            </b-col>
            <b-col class="mt-4">
              <b-button block variant="secondary" v-b-modal.modal-city>
              Agregar ciudad
              </b-button>
            </b-col>
            </b-row>
            Nombre
            <b-form-input v-model="tour.name" :state="tour.name!=''"></b-form-input>
            Categoria
            <b-form-input v-model="tour.category" :state="tour.category!=''"></b-form-input>
          </b-col>
          <b-col>
            Descripcion
            <b-form-textarea no-resize rows=4 v-model="tour.description" :state="tour.description!=''"></b-form-textarea>
            <b-row>
              <b-col>
                Fecha
                <b-form-datepicker dark label-help="" :state="tour.date!=''" 
                placeholder=" " hide-header 
                v-model="tour.date" :min="min"  
                :date-format-options="{ year: 'numeric', month: 'numeric', day: 'numeric' }"
                locale="es"></b-form-datepicker>
              </b-col>
              <b-col>
                Duración
                <b-form-timepicker placeholder=" " label-close-button="Cerrar" :state="tour.duration!=''" v-model="tour.duration" hide-header></b-form-timepicker>
              </b-col>
            </b-row>
            <b-row>
              <b-col>
                Cupo
                <b-form-spinbutton v-model="tour.quota" min:1 value=1 ></b-form-spinbutton>
              </b-col>
              <b-col>
                Precio en Dolares
                <b-form-input v-model="tour.price" min:1 type="number"></b-form-input>
              </b-col>
            </b-row>
          </b-col>
          <b-col>
            <b-row>
                Incluye
              <b-form-input v-model="tour.includes" :state="tour.includes!=''"></b-form-input>
                No incluye
              <b-form-input v-model="tour.notIncludes" :state="tour.notIncludes!=''"></b-form-input>
                Imagenes
                <b-form-file id="images" v-model="imagesForm" multiple accept="image/*" 
                placeholder='Seleccione multiples imagenes' browse-text='' @change="loadFiles"></b-form-file>
            </b-row>
            <b-row class="mt-4">
              <b-col>
                Imagen Principal
              </b-col>
              <b-col>
                <b-form-select v-model="mainImageSelected" :options="imageNames" @change="updateMainImage"></b-form-select>
              </b-col>
            </b-row>
          </b-col>
        </b-row>
        <b-row class="mt-3 text-center">
          <b-col>
            <b-button variant="secondary" @click="resetForm">
              Limpiar
            </b-button>
          </b-col>
          <b-col>
            <b-button variant="primary" @click="createNewTour">
              Registrar
            </b-button>
          </b-col>
          <b-col>
            <b-button variant="danger" @click="resetImages()">
              <b-icon-trash variant="light"></b-icon-trash>
              Eliminar Imagenes 
            </b-button>
          </b-col>
        </b-row>
      </b-card>
    </b-card>
  </div>
</template>

<script>

export default {
  name: 'CreateTour',
  data() {
    if(!this.$session.has('user') || !this.$session.get('user').admin){
      window.location.href = 'http://localhost:8002';
    }
    const minDate = new Date()
    return {
      min: minDate,
      messageAlert: "",
      showAlert: 0,
      alertvariant: "",
      secShowAlert: 5,
      showOverlay: false,
      countries: [],
      cities: [],
      countrySelected: null,
      citySelected: null,
      mainImageSelected: -1,
      imagesForm: [],
      newCountry: '',
      newCity: '',
      tour :{
        city: null,
        name: '',
        category: '',
        description: '',
        date: '',
        quota: 1,
        duration: '',
        price: 1,
        includes: '',
        notIncludes: '',
        images: []
      }
    }
  },
  async created(){
    const response = await fetch('http://localhost:8001/countries?complete=true', {method: 'GET'});
    let countriesFetch = (await response.json());
    countriesFetch.forEach(country => {
      this.countries.push({text:country.name, value:country})
    });
  },
  computed: {
    imageNames(){
      let names = [{text:'Seleccione una imagen', value:-1}]
      for(let i in this.imagesForm){
        names.push({text:this.imagesForm[i].name, value:i})
      }
      return names;
    }
  },
  watch:{
    mainImageSelected: function(newMainImage, oldMainImage){
      if(oldMainImage != -1){
        this.tour.images[oldMainImage].mainPhoto = 0;
      }
      this.tour.images[newMainImage].mainPhoto = 1;
    }
  },
  methods: {
    loadFiles(){
      this.tour.images = [];
      let files = document.getElementById('images').files
      for(let file of files){
        let reader = new FileReader();
        reader.readAsDataURL(file);
        var myTourImages = this.tour.images;
        reader.onload = function () {
          myTourImages.push({mainPhoto:0, photoBase64: reader.result.split(',')[1]})
        };
        reader.onerror = function (error) {
          console.log('Error: ', error);
        };
      }
    },
    countDownChanged(dismissCountDown) {
        this.showAlert = dismissCountDown
    },
    resetForm(){
      this.countrySelected = null;
      this.citySelected = null;
      this.mainImageSelected = -1;
      this.cities = [];
      this.tour.name = '';
      this.tour.category = '';
      this.tour.date = '';
      this.tour.description = '';
      this.tour.duration = '';
      this.tour.quota = 1;
      this.tour.price = 1;
      this.tour.includes = '';
      this.tour.notIncludes = '';
      this.imagesForm = [];
      this.images = [];
    },
    resetModal(){
      this.newCountry = '';
      this.newCity = '';
    },
    resetImages(){
      this.tour.images = [];
      this.mainImageSelected = -1;
      this.imagesForm = [];
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
        this.$emit("updateOverlay", false);
        this.messageAlert = "El servidor no responde";
        this.alertvariant = "danger";
      }
      this.showAlert = this.secShowAlert;
    },
    async createNewCity(){
      if(this.newCity == '' || this.countrySelected == null){
        this.messageAlert = "Debes ingresar el nombre de la ciudad y elegir un pais";
        this.alertvariant = "warning";
        this.showAlert = this.secShowAlert;
        return;
      }
      try {
        this.$emit("updateOverlay", true);
        const response = await fetch('http://localhost:8001/city', {
          method: 'POST',
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            name: this.newCity,
            country: {id: this.countrySelected.id }
          })
        });
        this.$emit("updateOverlay", false);
        if(response.status == 404){
          this.messageAlert = "Registro fallido, ya existe esta ciudad en ese pais";
          this.alertvariant = "danger";
          this.showAlert = this.secShowAlert;
          return;
        }
        this.messageAlert = "Registro de ciudad exitoso";
        this.alertvariant = "success";
        this.updateCountryCities()
      } catch (err) {
        this.$emit("updateOverlay", false);
        this.messageAlert = "El servidor no responde";
        this.alertvariant = "danger";
      }
      this.showAlert = this.secShowAlert;
    },
    loadCities(){
      this.cities = [];
      this.countrySelected.cities.forEach(city => {
        this.cities.push({text:city.name, value:city})
      });
    },
    includeCity(){
      this.tour.city = {name: this.citySelected.name, id: this.citySelected.id}
    },
    async updateCountryCities(){
      this.countries = [];
      const response = await fetch('http://localhost:8001/countries?complete=true', {method: 'GET'});
      let countriesFetch = (await response.json());
      countriesFetch.forEach(country => {
        this.countries.push({text:country.name, value:country})
      });
    },
    async createNewTour(){
      if( this.countrySelected == null ||
          this.citySelected == null ||
          this.tour.name == '' ||
          this.tour.category == '' ||
          this.tour.date == '' ||
          this.tour.description == '' ||
          this.tour.duration == '' ||
          this.tour.includes == '' ||
          this.tour.notIncludes == ''){
        this.messageAlert = "Debes ingresar todos los datos";
        this.alertvariant = "warning";
        this.showAlert = this.secShowAlert;
        return;
      }
      if(this.tour.images.length == 0){
        this.messageAlert = "Debe subir al menos una imagen del tour";
        this.alertvariant = "warning";
        this.showAlert = this.secShowAlert;
        return;
      }
      if(this.mainImageSelected == -1){
        this.messageAlert = "Debe seleccionar una imagen principal";
        this.alertvariant = "warning";
        this.showAlert = this.secShowAlert;
        return;
      }
      try {
        this.$emit("updateOverlay", true);
        const response = await fetch('http://localhost:8001/tour', {
          method: 'POST',
          headers: { "Content-Type": "application/json"},
          body: JSON.stringify(this.tour)
        });
        this.$emit("updateOverlay", false);
        if(response.status == 404){
          this.messageAlert = "Registro fallido";
          this.alertvariant = "danger";
          this.showAlert = this.secShowAlert;
          return;
        }
        this.messageAlert = "El Tour se ha agregado con exito";
        this.alertvariant = "success";
        //this.resetForm();
      } catch (err) {
        this.$emit("updateOverlay", false);
        this.messageAlert = "El servidor no responde";
        this.alertvariant = "danger";
      }
      this.showAlert = this.secShowAlert;
    }
  }
}
</script>

