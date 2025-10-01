import { useState } from 'react'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className="min-h-screen bg-gray-50 flex items-center justify-center">
      <div className="max-w-md w-full bg-white rounded-lg shadow-md p-6">
        <div className="text-center mb-6">
          <h1 className="text-3xl font-bold text-gray-900">WiraDayCare</h1>
          <p className="text-gray-600 mt-2">🚀 Hot Reload Test - Updated!</p>
        </div>
        
        <div className="text-center">
          <button 
            onClick={() => setCount((count) => count + 1)}
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
          >
            Count is {count}
          </button>
          <p className="text-sm text-gray-500 mt-4">
            Click the button to test React state management
          </p>
        </div>

        <div className="mt-6 p-4 bg-green-50 rounded">
          <h2 className="font-semibold text-green-800">Story 1 Complete ✅</h2>
          <ul className="text-sm text-green-700 mt-2 space-y-1">
            <li>✓ Vite + React + TypeScript</li>
            <li>✓ TailwindCSS configured</li>
            <li>✓ Ready for Docker</li>
          </ul>
        </div>
      </div>
    </div>
  )
}

export default App