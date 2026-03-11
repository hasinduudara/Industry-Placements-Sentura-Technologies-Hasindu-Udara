import { useState, useEffect } from 'react';

// TypeScript Interface for Country
interface Country {
    name: string;
    capital: string;
    region: string;
    population: number;
    flag: string;
}

function App() {
    const [countries, setCountries] = useState<Country[]>([]);
    const [searchTerm, setSearchTerm] = useState<string>('');

    useEffect(() => {
        const fetchCountries = async () => {
            try {
                const url = searchTerm
                    ? `http://localhost:8080/api/countries?search=${searchTerm}`
                    : 'http://localhost:8080/api/countries';
                const response = await fetch(url);
                const data = await response.json();
                setCountries(data);
            } catch (error) {
                console.error("Error fetching data:", error);
            }
        };

        // Debounce search slightly for better performance
        const delayDebounceFn = setTimeout(() => {
            fetchCountries();
        }, 300);

        return () => clearTimeout(delayDebounceFn);
    }, [searchTerm]);

    return (
        <div className="max-w-5xl mx-auto p-6 font-sans text-gray-800">
            <h1 className="text-3xl font-bold mb-6 text-center text-blue-700">Countries Directory</h1>

            {/* Search Input */}
            <input
                type="text"
                placeholder="Search countries..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                className="w-full p-3 mb-6 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
            />

            {/* Countries Table */}
            <div className="overflow-x-auto shadow-md rounded-lg">
                <table className="w-full border-collapse bg-white">
                    <thead className="bg-gray-100 border-b-2 border-gray-200">
                        <tr>
                            <th className="p-4 text-left font-semibold">Flag</th>
                            <th className="p-4 text-left font-semibold">Name</th>
                            <th className="p-4 text-left font-semibold">Capital</th>
                            <th className="p-4 text-left font-semibold">Region</th>
                            <th className="p-4 text-left font-semibold">Population</th>
                        </tr>
                    </thead>
                    <tbody>
                        {countries.map((country) => (
                            <tr key={country.name} className="border-b hover:bg-gray-50">
                                <td className="p-4">
                                    <img src={country.flag} alt={`${country.name} flag`} className="w-10 h-6 object-cover rounded" />
                                </td>
                                <td className="p-4 font-medium">{country.name}</td>
                                <td className="p-4">{country.capital}</td>
                                <td className="p-4">{country.region}</td>
                                <td className="p-4">{country.population.toLocaleString()}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default App;
