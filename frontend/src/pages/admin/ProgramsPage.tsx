export function ProgramsPage() {
  return (
    <div>
      <div className="mb-6">
        <h1 className="text-2xl font-bold text-gray-900 dark:text-white">Programs Management</h1>
        <p className="text-gray-600 dark:text-gray-300 mt-1">Create and manage educational programs</p>
      </div>

      <div className="bg-white dark:bg-gray-800 shadow rounded-lg p-6">
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-lg font-medium text-gray-900 dark:text-white">All Programs</h2>
          <button className="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded-md">
            Create Program
          </button>
        </div>
        
        <div className="border-2 border-dashed border-gray-200 dark:border-gray-600 rounded-lg p-8 text-center">
          <div className="text-gray-400 dark:text-gray-500 mb-2">
            <svg className="mx-auto h-12 w-12" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 6.253v13m0-13C10.832 5.477 9.246 5 7.5 5S4.168 5.477 3 6.253v13C4.168 18.477 5.754 18 7.5 18s3.332.477 4.5 1.253m0-13C13.168 5.477 14.754 5 16.5 5c1.746 0 3.332.477 4.5 1.253v13C20.832 18.477 19.246 18 17.5 18c-1.746 0-3.332.477-4.5 1.253" />
            </svg>
          </div>
          <h3 className="text-sm font-medium text-gray-900 dark:text-white mb-1">No programs yet</h3>
          <p className="text-sm text-gray-500 dark:text-gray-400 mb-4">
            Get started by creating your first educational program.
          </p>
          <button className="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded-md text-sm">
            Create First Program
          </button>
        </div>

        <div className="mt-6 p-4 bg-yellow-50 dark:bg-yellow-900 rounded">
          <p className="text-sm text-yellow-800 dark:text-yellow-200">
            💡 <strong>Coming Soon:</strong> Program creation form, age group management, curriculum planning, and program scheduling will be available in Story 4.
          </p>
        </div>
      </div>
    </div>
  );
}