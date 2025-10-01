import { Child, CreateChildRequest } from '../types/child';
import { authService } from './authService';

const API_BASE_URL = 'http://localhost:8081';

class ChildService {
  private async getHeaders(): Promise<HeadersInit> {
    const token = authService.getToken();
    return {
      'Content-Type': 'application/json',
      ...(token && { 'Authorization': `Bearer ${token}` })
    };
  }

  async getAllChildren(): Promise<Child[]> {
    try {
      const headers = await this.getHeaders();
      const response = await fetch(`${API_BASE_URL}/children`, {
        method: 'GET',
        headers
      });

      if (!response.ok) {
        throw new Error(`Failed to fetch children: ${response.statusText}`);
      }

      return await response.json();
    } catch (error) {
      console.error('Error fetching children:', error);
      throw error;
    }
  }

  async getChildById(id: number): Promise<Child> {
    try {
      const headers = await this.getHeaders();
      const response = await fetch(`${API_BASE_URL}/children/${id}`, {
        method: 'GET',
        headers
      });

      if (!response.ok) {
        throw new Error(`Failed to fetch child: ${response.statusText}`);
      }

      return await response.json();
    } catch (error) {
      console.error('Error fetching child:', error);
      throw error;
    }
  }

  async createChild(childData: CreateChildRequest): Promise<Child> {
    try {
      const headers = await this.getHeaders();
      const response = await fetch(`${API_BASE_URL}/children`, {
        method: 'POST',
        headers,
        body: JSON.stringify(childData)
      });

      if (!response.ok) {
        throw new Error(`Failed to create child: ${response.statusText}`);
      }

      return await response.json();
    } catch (error) {
      console.error('Error creating child:', error);
      throw error;
    }
  }

  async updateChild(id: number, childData: Partial<CreateChildRequest>): Promise<Child> {
    try {
      const headers = await this.getHeaders();
      const response = await fetch(`${API_BASE_URL}/children/${id}`, {
        method: 'PUT',
        headers,
        body: JSON.stringify(childData)
      });

      if (!response.ok) {
        throw new Error(`Failed to update child: ${response.statusText}`);
      }

      return await response.json();
    } catch (error) {
      console.error('Error updating child:', error);
      throw error;
    }
  }

  async deleteChild(id: number): Promise<void> {
    try {
      const headers = await this.getHeaders();
      const response = await fetch(`${API_BASE_URL}/children/${id}`, {
        method: 'DELETE',
        headers
      });

      if (!response.ok) {
        throw new Error(`Failed to delete child: ${response.statusText}`);
      }
    } catch (error) {
      console.error('Error deleting child:', error);
      throw error;
    }
  }
}

export const childService = new ChildService();