#include "Organism.h"

void Organism::setPos(int x, int y)
{
    this->xPos = x;
    this->yPos = y;
}
void Organism::colision(Organism *other) {}
void Organism::setStrength(int str) { this->strength = str; }
void Organism::setInitiative(int ini) { this->initiative = ini; }
void Organism::setAge(int newAge) { this->age = newAge; }
void Organism::die() { this->isAlive = false; }
void Organism::drawOrg()
{
    std::cout << getSymbol();
}
char Organism::getSymbol() const { return this->symbol; }
int Organism::getXPos() const { return this->xPos; }
int Organism::getYPos() const { return this->yPos; }
int Organism::getPrevXPos() const { return this->prevX; }
int Organism::getPrevYPos() const { return this->prevY; }
int Organism::getInitiative() const { return this->initiative; }
int Organism::getStrength() const { return this->strength; }
int Organism::getAge() const { return this->age; }
Organism::AllOrganisms Organism::getType() const { return this->type; }
bool Organism::checkIfIsAlive() const { return this->isAlive; }
Organism::Organism(World &wR, const int &xPos, const int &yPos, const char &symbol, const int &strength, const int &initiative, AllOrganisms type)
    : worldRef(wR), xPos(xPos), yPos(yPos), symbol(symbol), strength(strength), initiative(initiative), type(type) {}

bool Organism::specialAbility(Organism *other) { return false; }
bool Organism::isAnAnimal() const { return false; }
std::string Organism::getTypeString() const { return this->typeS; }
Organism::~Organism() {}