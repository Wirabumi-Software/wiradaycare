// Authentication types
export interface LoginRequest {
  username: string;
  password: string;
}

export interface AuthResponse {
  access_token: string;
  token_type: string;
  expires_in: number;
}

export interface User {
  username: string;
  roles: string[];
}

// JWT token payload (decoded)
export interface JwtPayload {
  sub: string; // UUID
  preferred_username: string; // actual username
  realm_access: {
    roles: string[];
  };
  exp: number;
  iat: number;
}