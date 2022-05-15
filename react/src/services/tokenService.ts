class TokenService {
  private readonly accessTokenKey = "accessToken";
  private readonly refreshTokenKey = "refreshToken";

  get accessToken() {
    const accessToken = localStorage.getItem(this.accessTokenKey);
    if (!accessToken) throw new Error(`${this.accessTokenKey} not set`);
    return JSON.parse(accessToken);
  }

  set accessToken(token: string) {
    localStorage.setItem(this.accessTokenKey, token);
  }

  get refreshToken() {
    const refreshToken = localStorage.getItem(this.refreshTokenKey);
    if (!refreshToken) throw new Error(`${this.refreshTokenKey} not set`);
    return JSON.parse(refreshToken);
  }

  set refreshToken(token: string) {
    localStorage.setItem(this.refreshTokenKey, token);
  }

  get tokensExist() {
    return this.accessToken !== null && this.refreshToken !== null
  }

  deleteTokens() {
    this.accessToken = ""
    this.refreshToken = ""
  }
}

export default new TokenService();