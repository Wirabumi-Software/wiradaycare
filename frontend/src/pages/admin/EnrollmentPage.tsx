export function EnrollmentPage() {
  return (
    <div>
      <div className="mb-6">
        <h1 className="text-2xl font-bold text-gray-900 dark:text-white">Enrollment Management</h1>
        <p className="text-gray-600 dark:text-gray-300 mt-1">Enroll children in programs and classrooms</p>
      </div>

      <div className="bg-white dark:bg-gray-800 shadow rounded-lg p-6">
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-lg font-medium text-gray-900 dark:text-white">Enrollment Status</h2>
          <button className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-md">
            New Enrollment
          </button>
        </div>
        
        <div className="border-2 border-dashed border-gray-200 dark:border-gray-600 rounded-lg p-8 text-center">
          <div className="text-gray-400 dark:text-gray-500 mb-2">
            <svg className="mx-auto h-12 w-12" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
            </svg>
          </div>
          <h3 className="text-sm font-medium text-gray-900 dark:text-white mb-1">No enrollments yet</h3>
          <p className="text-sm text-gray-500 dark:text-gray-400 mb-4">
            Start enrolling children in programs and classrooms.
          </p>
          <button className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-md text-sm">
            Create First Enrollment
          </button>
        </div>

        <div className="mt-6 p-4 bg-yellow-50 dark:bg-yellow-900 rounded">
          <p className="text-sm text-yellow-800 dark:text-yellow-200">
            💡 <strong>Coming Soon:</strong> Enrollment form, program selection, and status management will be available in Story 7.
          </p>
        </div>
      </div>
    </div>
  );
}