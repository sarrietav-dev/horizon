/**
 * Only thrown when authenticating the user.
 */
export class FailedAuthenticationException extends Error {
  constructor(message?: string) {
    super(message);
  }
}
