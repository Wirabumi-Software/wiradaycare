import { LoginRequest, AuthResponse, JwtPayload, User } from '../types/auth';

// Base API configuration
const API_BASE = '/api'; // Will be proxied to backend via Vite config

class AuthService {
  private tokenKey = 'wiradaycare_token';
  private userKey = 'wiradaycare_user';

  // Login method - calls the existing /auth/login endpoint
  async login(credentials: LoginRequest): Promise<AuthResponse> {
    const response = await fetch(`${API_BASE}/auth/login?username=${credentials.username}&password=${credentials.password}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      }
    });

    if (!response.ok) {
      throw new Error('Login failed');
    }

    const authData: AuthResponse = await response.json();
    
    // Store token and user info
    this.setToken(authData.access_token);
    const user = this.decodeToken(authData.access_token);
    this.setUser(user);

    return authData;
  }

  // Logout method
  logout(): void {
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem(this.userKey);
  }

  // Token management
  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  private setToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
  }

  // User management
  getUser(): User | null {
    const userStr = localStorage.getItem(this.userKey);
    return userStr ? JSON.parse(userStr) : null;
  }

  private setUser(user: User): void {
    localStorage.setItem(this.userKey, JSON.stringify(user));
  }

  // Check if user is authenticated
  isAuthenticated(): boolean {
    const token = this.getToken();
    if (!token) return false;

    try {
      const payload = this.decodeTokenPayload(token);
      return payload.exp * 1000 > Date.now(); // Check if token is not expired
    } catch {
      return false;
    }
  }

  // Decode JWT token to extract user info
  private decodeToken(token: string): User {
    const payload = this.decodeTokenPayload(token);
    
    return {
      username: payload.preferred_username || payload.sub, // fallback to sub if preferred_username not available
      roles: payload.realm_access?.roles || []
    };
  }

  // Decode JWT payload (simple base64 decode)
  private decodeTokenPayload(token: string): JwtPayload {
    const parts = token.split('.');
    if (parts.length !== 3) {
      throw new Error('Invalid token format');
    }

    const payload = parts[1];
    const decoded = atob(payload.replace(/-/g, '+').replace(/_/g, '/'));
    return JSON.parse(decoded);
  }

  // Check if user has specific role
  hasRole(role: string): boolean {
    const user = this.getUser();
    return user?.roles.includes(role) || false;
  }

  // Get authorization header for API calls
  getAuthHeader(): HeadersInit {
    const token = this.getToken();
    return token ? { 'Authorization': `Bearer ${token}` } : {};
  }
}

export const authService = new AuthService();