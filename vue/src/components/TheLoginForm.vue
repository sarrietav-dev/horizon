<template>
  <div class="card">
    <div class="card-body">
      <h2 class="text-center my-3">Iniciar Sesion</h2>
      <form class="container" method="POST">
        <div class="row my-2">
          <div class="col">
            <input
              aria-label="Email"
              class="form-control"
              placeholder="Email"
              type="email"
            />
          </div>
        </div>
        <div class="row my-2">
          <div class="col">
            <input
              aria-label="Contraseña"
              class="form-control"
              placeholder="Contraseña"
              type="password"
            />
          </div>
        </div>
        <div class="d-flex justify-content-center align-items-center">
          <button class="btn btn-primary" type="submit">Iniciar Sesion</button>
        </div>
      </form>
    </div>
  </div>
</template>
