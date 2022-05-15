const TheLoginForm = () =>
  <div>
    <div className="card-body">
      <h2 className="text-center my-3">Iniciar Sesion</h2>
      <form method="POST" className="container">
        <div className="row my-2">
          <div className="col"><input type="email" className="form-control"/></div>
        </div>
        <div className="row my-2">
          <div className="col"><input type="password" className="form-control"/></div>
        </div>
        <div className="d-flex justify-content-center align-items-center">
          <button className="btn btn-primary">Iniciar Sesion</button>
        </div>
      </form>
    </div>
  </div>

export default TheLoginForm;