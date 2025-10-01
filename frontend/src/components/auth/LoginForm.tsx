import { useState } from 'react';
import { authService } from '../../services/authService';
import { ThemeToggle } from '../ui/ThemeToggle';

interface LoginFormProps {
  onLoginSuccess: () => void;
}

export function LoginForm({ onLoginSuccess }: LoginFormProps) {
  const [username, setUsername] = useState('alice');
  const [password, setPassword] = useState('alice123');
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsLoading(true);
    setError('');

    try {
      await authService.login({ username, password });
      onLoginSuccess();
    } catch (err) {
      setError('Login failed. Please check your credentials.');
      console.error('Login error:', err);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gray-50 dark:bg-gray-900 flex items-center justify-center transition-colors">
      <div className="max-w-md w-full bg-white dark:bg-gray-800 rounded-lg shadow-md p-6">
        <div className="flex justify-end mb-4">
          <ThemeToggle />
        </div>
        <div className="text-center mb-6">
          <h1 className="text-3xl font-bold text-gray-900 dark:text-white">WiraDayCare</h1>
          <p className="text-gray-600 dark:text-gray-300 mt-2">Please sign in to continue</p>
        </div>

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label htmlFor="username" className="block text-sm font-medium text-gray-700 dark:text-gray-300">
              Username
            </label>
            <input
              id="username"
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
              className="mt-1 block w-full px-3 py-2 border border-gray-300 dark:border-gray-600 rounded-md shadow-sm bg-white dark:bg-gray-700 text-gray-900 dark:text-white focus:outline-none focus:ring-blue-500 focus:border-blue-500"
            />
          </div>

          <div>
            <label htmlFor="password" className="block text-sm font-medium text-gray-700 dark:text-gray-300">
              Password
            </label>
            <input
              id="password"
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              className="mt-1 block w-full px-3 py-2 border border-gray-300 dark:border-gray-600 rounded-md shadow-sm bg-white dark:bg-gray-700 text-gray-900 dark:text-white focus:outline-none focus:ring-blue-500 focus:border-blue-500"
            />
          </div>

          {error && (
            <div className="bg-red-50 dark:bg-red-900 border border-red-200 dark:border-red-700 text-red-700 dark:text-red-300 px-4 py-3 rounded">
              {error}
            </div>
          )}

          <button
            type="submit"
            disabled={isLoading}
            className="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 disabled:bg-gray-400 disabled:cursor-not-allowed"
          >
            {isLoading ? 'Signing in...' : 'Sign in'}
          </button>
        </form>

        <div className="mt-6 p-4 bg-blue-50 dark:bg-blue-900 rounded">
          <h3 className="font-semibold text-blue-800 dark:text-blue-300">Test Accounts</h3>
          <div className="text-sm text-blue-700 dark:text-blue-300 mt-2 space-y-1">
            <div>👨‍💼 <strong>Admin:</strong> alice / alice123</div>
            <div>👥 <strong>Caregiver:</strong> bob / bob123</div>
          </div>
          <button
            type="button"
            onClick={() => {
              localStorage.removeItem('wiradaycare_token');
              localStorage.removeItem('wiradaycare_user');
              window.location.reload();
            }}
            className="mt-2 text-xs bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600"
          >
            Clear Storage & Refresh
          </button>
        </div>
      </div>
    </div>
  );
}