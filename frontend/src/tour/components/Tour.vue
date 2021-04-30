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
    <div v-if="fetchData">
      <b-card id="card-tour" bg-variant="dark">
        <b-card bg-variant="light">
          <b-row class="justify-content-md-center"> 
            <b-col cols=10>
              <h2>              
                  {{tour.city.country.name}} - {{tour.city.name}}: {{tour.name}}
              </h2>
            </b-col>
            <b-col cols=2>     
              <b-button variant="warning" @click="returnIndex">
                <b-icon-arrow90deg-left></b-icon-arrow90deg-left> 
                  Volver
              </b-button>
            </b-col>
          </b-row> 
          <b-row class="justify-content-md-begin"> 
            <b-col cols=1>
            </b-col>
            <b-col cols=3 v-show="tour.reviews > 0">
              <b-form-rating v-model="tour.rating" variant="warning" stars="5" precision="2" show-value-max readonly show-value no-border></b-form-rating>
            </b-col>
            <b-col cols=4 align-self="center">
              <b-badge variant="primary">{{ tour.reviews }} Opiniones </b-badge> 
            </b-col>
            <b-col cols=3>
              <template v-if="isLogin">
                <template v-if="tour.liked">
                  <b-button block variant="light" @click="removeLike(tour)">
                    <b-icon-bookmark-heart-fill class="like-button" variant="danger"></b-icon-bookmark-heart-fill> 
                    Eliminar de favoritos
                  </b-button>
                </template>
                <template v-else>
                  <b-button block variant="light" @click="addLike(tour)">
                    <b-icon-bookmark-heart class="like-button" variant="secondary"></b-icon-bookmark-heart> 
                    Añadir a favoritos
                  </b-button>
                </template>
              </template>
            </b-col>
          </b-row>
          <b-row class="justify-content-md-center">
            <b-carousel id="carousel"
                :interval="4000"
                controls
                indicators
                img-width="1800"
                img-height="700"
              >
              <div v-for="(image, idx) in tour.images" :key="idx">
                <b-carousel-slide img-blank >
                  <b-img :src="'data:image/jpeg;base64,'+image.photo"  fluid alt="Main image Tour"></b-img>
                </b-carousel-slide>
              </div>
            </b-carousel>
          </b-row>
          <b-row class="justify-content-md-center mt-3">
            <b-col cols=6>
              <h3><strong>Informacion general</strong></h3>
              Descripcion: {{tour.description}} <br>
              <b-icon-clock-history></b-icon-clock-history>
              Duración: {{ tour.duration.split(':')[0]}} : {{tour.duration.split(':')[1]}} 
              <br>
              <b-icon-list-check></b-icon-list-check>
              Categoria: {{ tour.category }} <br>
              <b-icon-calendar></b-icon-calendar>
              Fecha: {{ new Date(tour.date).toLocaleString().split(' ')[0]}} 
              <br>
              <b-icon-people-fill></b-icon-people-fill>
              Cupos: {{ tour.quota }} <br>
              <b-icon-check-circle></b-icon-check-circle>
              Incluye: {{ tour.includes }} <br>
              <b-icon-x-circle></b-icon-x-circle>
              No incluye: {{ tour.notIncludes }} <br>
            </b-col>
            <b-col cols=4>
              <b-card bg-variant="light" border-variant="light">
                <b-row>
                  <b-col cols=11 class="text-right">
                    Desde                    
                    <b-button squared disabled variant="light"> <h4><strong>${{tour.price}}</strong></h4></b-button>
                    por persona
                  </b-col>
                </b-row>
                <b-row v-show="isLogin">
                  <b-col class="text-right">
                    <b-button squared disabled variant="light"> Tiquetes: </b-button>                  
                  </b-col>
                  <b-col class="text-left">
                    <b-form-spinbutton size="lg" v-model.number="tickets" :min=0 :max=tour.quota></b-form-spinbutton>
                  </b-col>
                </b-row>
                <b-row v-if="isLogin" align-self="center" class="mt-3">
                  <b-col cols=10 class="text-center">
                    <b-button variant="primary" @click="addCart(tour)">Agregar al carrito</b-button>
                  </b-col>
                </b-row>
                <b-row v-else class="justify-content-md-center mt-3">
                  <b-col cols=10>
                    Inicia sesión para reservar
                  </b-col>
                </b-row>
              </b-card>
            </b-col>
          </b-row>
        </b-card>
      </b-card>
      <b-card bg-variant="dark" class="mt-4">
        <b-row v-if="tour.comments !== 0">
          <b-col cols=12 class="text-left">
              <div class=" text-dark">
                <h4>
                  <b-badge variant="primary">{{ tour.reviews }} Comentarios </b-badge> 
                </h4>
              </div>
          </b-col>
        </b-row>
        <b-card class="mt-2" v-for="(comment, idx) in tour.comments" :key="idx">
          <b-row class="justify-content-md-begin">
            <b-col cols=2>
              <b-form-rating v-model="comment.rating" precision="1" show-value stars=5 variant="warning" readonly no-border></b-form-rating>
            </b-col>
          </b-row>
          <b-row class="justify-content-md-center">
            <b-col>
              {{comment.description}}
            </b-col>
          </b-row>
          <b-row class="justify-content-md-begin">
            <b-col cols=1 align-self="center">
              <b-avatar size="3em" variant="info">
                <h5>{{ comment.user.name[0]}}{{comment.user.lastName[0] }} </h5>
              </b-avatar>
            </b-col>
            <b-col cols=4 align-self="center">
              <b-row>
                <em>Valoración de </em>
              </b-row> 
              <b-row>
                <strong>{{ comment.user.name }}  {{ comment.user.lastName }} - {{ comment.user.country.name }} </strong> 
              </b-row>
            </b-col>
          </b-row>
        </b-card>
      </b-card>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Tour',
  data() {
    if(!this.$session.has('idTour')){
      window.location.href = 'http://localhost:8002';
    }
    return {
      tour: {},
      fetchData: false,
      tickets: 0,
      messageAlert: "",
      showAlert: 0,
      alertvariant: "",
      secShowAlert: 5
    }
  },
  created(){
    this.loadTour();
  },
  computed: {
    isLogin(){
      return this.$session.has('user');
    }
  },
  methods: {
    async loadTour(){
      try {
        this.$emit("updateOverlay", true);
        let id_user = 0;
        if(this.$session.has('user')){
          id_user = this.$session.get('user').id
        }
        const response = await fetch(
          'http://localhost:8001/tours/' +
          this.$session.get('idTour') +
          '/users/' + id_user
        , {method: 'GET'});
        this.$emit("updateOverlay", false);
        this.tour = await response.json();
        this.fetchData = true;
      } catch (err) {
        this.$emit("updateOverlay", false);
        this.messageAlert = "El servidor no responde";
        this.alertvariant = "danger";
        this.showAlert = this.secShowAlert;
        setTimeout( () => { 
          window.location.href = 'http://localhost:8002/';
        },this.secShowAlert); 
      }
    },
    countDownChanged(dismissCountDown) {
        this.showAlert = dismissCountDown
    },
    async addLike(tour){
      if(this.$session.has('user')){
        var Id_User = this.$session.get('user').id;
        try {
          this.$emit("updateOverlay", true);
          const response = await fetch('http://localhost:8001/likes', {
            method: 'POST',
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            id: 0,
            tour: { id : tour.id },
            user: { id : Id_User }
          })
        });
        this.$emit("updateOverlay", false);
        console.log(await response.json());
        tour.liked = true;
        } catch (err) {
          this.$emit("updateOverlay", false);
          this.messageAlert = "El servidor no responde";
          this.alertvariant = "danger";
          this.showAlert = this.secShowAlert; 
        }
      }
    },
    async removeLike(tour){
      if(this.$session.has('user')){
        var Id_User = this.$session.get('user').id;
        try {
          this.$emit("updateOverlay", true);
          const response = await fetch('http://localhost:8001/likes/tours/' + tour.id +
          '/users/' + Id_User, {
          method: 'DELETE',
          headers: {
            "Content-Type": "application/json"
          }
        });
        this.$emit("updateOverlay", false);
        console.log(await response.json());
        tour.liked = false;
        } catch (err) {
          this.$emit("updateOverlay", false);
          this.messageAlert = "El servidor no responde";
          this.alertvariant = "danger";
          this.showAlert = this.secShowAlert;
        }
      }
    },
    returnIndex(){
      this.$session.remove('tour');
      window.location.href = 'http://localhost:8002';
    },
    addCart(){
      if(this.tickets == 0){
        this.messageAlert = "Elija la cantidad de tiquetes a comprar antes de agregar al carro";
        this.alertvariant = "warning";
        this.showAlert = this.secShowAlert;
        window.scrollTo({
          top: 0,
          left: 0,
          behavior: 'smooth'
        });
        return;
      }
      if(this.$session.has("user")) {
        var cart = this.$session.get("cart");
        if(!cart){
          cart = []
        }
        var finded = false;
        for(let item of cart){
          if(item.tour.id == this.tour.id){
            item.tickets += this.tickets;
            item.total = item.tickets * item.price;
            finded = true;
            if(item.tickets > this.tour.quota){
              this.messageAlert = "Lo sentimos, no hay suficientes cupos para este tour, disponemos de " + this.tour.quota + " cupos";
              this.alertvariant = "danger";
              this.showAlert = this.secShowAlert;
              window.scrollTo({
                top: 0,
                left: 0,
                behavior: 'smooth'
              });
              return;
            }
            break; 
          }
        }
        if(!finded){
          if(this.tickets > this.tour.quota){
              this.messageAlert = "Lo sentimos, no hay suficientes cupos para este tour, disponemos de " + this.tour.quota + " cupos";
              this.alertvariant = "danger";
              this.showAlert = this.secShowAlert;
              window.scrollTo({
                top: 0,
                left: 0,
                behavior: 'smooth'
              });
              return;
          }
          cart.push( 
            { tour:{id : this.tour.id, quota: this.tour.quota}, 
              place : this.tour.city.country.name + ' - ' + this.tour.city.name, 
              date : new Date(this.tour.date).toLocaleString().split(' ')[0], 
              name : this.tour.name, 
              price : this.tour.price, 
              tickets : this.tickets,
              total : this.tickets * this.tour.price
            }
          )
        }
        this.$session.set("cart", cart);
        this.$parent.$children[0].cartItems = cart.length;
        this.messageAlert = "Se agregaron " + this.tickets + " tiquetes al carro para el tour " + this.tour.name;
        this.messageAlert += " para reservar y pagar dirijase al Carro";
        this.alertvariant = "success";
        this.showAlert = this.secShowAlert * 2;
        this.tickets = 0;
        window.scrollTo({
          top: 0,
          left: 0,
          behavior: 'smooth'
        });
      }
    },
  }
}

</script>

<style scoped>

#card-tour{
  min-width: 400px;
}

#carousel{
  background-color: #35383E;
}

</style>
